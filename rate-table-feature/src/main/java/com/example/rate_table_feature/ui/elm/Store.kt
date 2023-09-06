package com.example.rate_table_feature.ui.elm

import vivid.money.elmslie.core.store.ElmStore

internal fun rateStoreFactory() =
    ElmStore<RatesScreenEvent, RatesScreenState, RatesScreenEffect, RatesScreenCommand>(
        initialState = RatesScreenState(isLoading = true),
        actor = RatesScreenActor(),
        reducer = RatesScreenReducer()
    ).start()