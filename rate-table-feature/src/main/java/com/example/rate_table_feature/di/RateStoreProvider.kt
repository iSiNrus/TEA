package com.example.rate_table_feature.di

import com.example.rate_table_feature.ui.elm.RatesScreenEffect
import com.example.rate_table_feature.ui.elm.RatesScreenEvent
import com.example.rate_table_feature.ui.elm.RatesScreenState
import com.example.rate_table_feature.ui.elm.rateStoreFactory
import vivid.money.elmslie.core.store.Store
import javax.inject.Provider

class RateStoreProvider : Provider<Store<RatesScreenEvent, RatesScreenEffect, RatesScreenState>> {
    override fun get(): Store<RatesScreenEvent, RatesScreenEffect, RatesScreenState> {
        return rateStoreFactory().apply {
            accept(RatesScreenEvent.UI.RefreshRates)
        }
    }

}