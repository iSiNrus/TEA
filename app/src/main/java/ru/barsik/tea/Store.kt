package ru.barsik.tea

import vivid.money.elmslie.core.store.ElmStore

fun storeFactory() = ElmStore<Event, ScreenState, MainScreenEffect, Command>(
    initialState = ScreenState(isLoading = true),
    reducer = MainScreenReducer(),
    actor = RateActor()
).start()