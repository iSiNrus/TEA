package ru.barsik.tea.ui.screens.main

import vivid.money.elmslie.core.store.ElmStore

fun storeFactory() = ElmStore<Event, MainScreenState, MainScreenEffect, Command>(
    initialState = MainScreenState(),
    reducer = MainScreenReducer(),
    actor = RateActor()
).start()