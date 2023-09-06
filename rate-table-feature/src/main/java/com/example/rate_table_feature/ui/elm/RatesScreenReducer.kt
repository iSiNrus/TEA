package com.example.rate_table_feature.ui.elm

import android.util.Log
import vivid.money.elmslie.core.store.dsl.DslReducer

class RatesScreenReducer :
    DslReducer<RatesScreenEvent, RatesScreenState, RatesScreenEffect, RatesScreenCommand>() {
    override fun Result.reduce(event: RatesScreenEvent): Any {
        return when (event) {
            is RatesScreenEvent.UI.RefreshRates -> {
                commands { +RatesScreenCommand.LoadRates }
                state { copy(isLoading = true) }
            }

            is RatesScreenEvent.Internal.RatesLoaded -> {
                if (event.ratesInfo.success) {
                    state {
                        copy(
                            isLoading = false,
                            rates = event.ratesInfo.rates,
                            base = event.ratesInfo.base ?: "None"
                        )
                    }
                    Log.d("REDUCER", "reduce: SUCCESS")
                    Log.d("REDUCER", event.ratesInfo.toString())
                } else {
                    state { copy(isLoading = false, rates = null) }
                    effects { +RatesScreenEffect.ShowLoadErrorMessage("Ошибка загрузки ёмаё") }
                    Log.d("REDUCER", event.ratesInfo.toString())
                }
            }

            is RatesScreenEvent.Internal.LoadError -> {
                state { copy(isLoading = false, rates = null) }
                effects {
                    +RatesScreenEffect.ShowLoadErrorMessage(
                        event.err.message ?: "Unknown error"
                    )
                }
                Log.d("Reducer", "reduce: ${event.err.message ?: "Unknown error"}")
            }
        }
    }
}