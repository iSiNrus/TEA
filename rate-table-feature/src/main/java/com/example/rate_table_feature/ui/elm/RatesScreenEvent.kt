package com.example.rate_table_feature.ui.elm

import com.example.rate_table_feature.domain.RatesInfo

sealed class RatesScreenEvent {

    sealed class UI : RatesScreenEvent() {
        object RefreshRates : UI()
    }

    sealed class Internal : RatesScreenEvent() {
        class RatesLoaded(val ratesInfo: RatesInfo) : Internal()
        class LoadError(val err: Throwable) : Internal()
    }

}
