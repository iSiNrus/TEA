package com.example.rate_table_feature.ui

import androidx.lifecycle.ViewModel
import com.example.rate_table_feature.ui.elm.RatesScreenEffect
import com.example.rate_table_feature.ui.elm.RatesScreenEvent
import com.example.rate_table_feature.ui.elm.RatesScreenState
import vivid.money.elmslie.core.store.Store
import javax.inject.Inject

class RatesViewModel : ViewModel() {
    @Inject
    lateinit var store: Store<RatesScreenEvent, RatesScreenEffect, RatesScreenState>
}