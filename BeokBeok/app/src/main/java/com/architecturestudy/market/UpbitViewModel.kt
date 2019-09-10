package com.architecturestudy.market

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitRepository
import com.architecturestudy.util.NumberFormatter
import com.architecturestudy.util.RxEventBus
import kotlinx.coroutines.launch

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    private var isDESC: Boolean = false

    val marketPriceList = MutableLiveData<List<Map<String, String>>>()
    val errMsg = MutableLiveData<Throwable>()
    val isSortByDESC = MutableLiveData<Boolean>()
    val selectedSortTypeList = MutableLiveData<List<Boolean>>()
        .apply { listOf(false, false, false, false) }

    fun showMarketPrice(prefix: String) = viewModelScope.launch {
        upBitRepository.getMarketPrice(
            prefix,
            onSuccess = {
                saveTickerInRoom(it)
                marketPriceList.value = NumberFormatter.convertTo(it)
            },
            onFail = {
                errMsg.value = it
            }
        )
    }

    fun sort(sortType: String) = viewModelScope.launch {
        setSelectedTypeList(sortType)
        isSortByDESC.value = isDESC
        upBitRepository.sort(
            sortType,
            isDESC,
            onSuccess = {
                RxEventBus.sendEvent(NumberFormatter.convertTo(it))
                isDESC = !isDESC
            },
            onFail = {
                RxEventBus.sendEvent(it)
            }
        )
    }

    private fun setSelectedTypeList(sortType: String) {
        when (sortType) {
            "market" -> selectedSortTypeList.value = listOf(true, false, false, false)
            "trade_price" -> selectedSortTypeList.value = listOf(false, true, false, false)
            "signed_change_rate" -> selectedSortTypeList.value = listOf(false, false, true, false)
            "acc_trade_price_24h" -> selectedSortTypeList.value = listOf(false, false, false, true)
        }
    }

    private fun saveTickerInRoom(tickerList: List<UpbitTicker>) = viewModelScope.launch {
        for (element in tickerList) {
            upBitRepository.saveTicker(element)
        }
    }
}