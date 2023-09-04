package ru.barsik.tea.ui.screens.main

import android.util.Log
import vivid.money.elmslie.core.store.dsl.DslReducer

class MainScreenReducer : DslReducer<Event, ScreenState, MainScreenEffect, Command>() {
    private val TAG = "REDUCER"
    override fun Result.reduce(event: Event): Any {
        return when (event) {
            is Event.UI.Init -> {
                state {
                    copy(
                        isLoading = true,
                        enterValue = "",
                        exitValue = "",
                        rate = "0.0",
                        messageError = null
                    )
                }
                commands { +Command.LoadRate }
            }

            is Event.UI.ResetValuesEvent -> {
                state { copy(
                    enterValue = "",
                    exitValue = "0.0"
                ) }
            }

            is Event.UI.TextChanged -> {
                commands { +Command.Calculate(event.newText) }
                state { copy(enterValue = event.newText) }
            }

            is Event.UI.BoomButtonClicked -> {
                effects { +MainScreenEffect.ShowBoom }
                Log.d(TAG, "reduce: Boom")
            }

            is Event.Internal.AmountCalculated -> {
                state { copy(exitValue = event.amount.toString(), messageError = null) }
                Log.d(TAG, "reduce: calculated ${event.amount}")
            }

            is Event.Internal.RateLoaded -> {
                Log.d(TAG, "reduce: ${event.rate}")
                state { copy(isLoading = false, rate=event.rate.toString()) }
            }

            is Event.Internal.InputError -> {
                state { copy(exitValue = "0.0", messageError = event.message)}
            }
        }
    }

}
