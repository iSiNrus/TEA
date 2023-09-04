package ru.barsik.tea

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import vivid.money.elmslie.core.store.Actor

class RateActor : Actor<Command, Event>() {
    private val TAG = "ACTOR"

    private fun calculateRublesToDollar(value: String) = flow<Float> {
        if (value.isBlank()) emit(0f)
        if (value.count { x -> x.isLetter() } > 0) error("Wrong format")
        else emit(value.toFloat() * RateRepository.getRateRublesToDollar())
    }

    override fun execute(command: Command): Flow<Event> {
        return when (command) {
            is Command.Calculate -> {
                calculateRublesToDollar(command.value).mapEvents(
                    eventMapper = { Event.Internal.AmountCalculated(it) },
                    errorMapper = { Event.Internal.InputError(it.message ?: "none") }
                )
            }

            is Command.ResetValues -> {
                flow { Event.UI.ResetValuesEvent }
            }

            is Command.LoadRate -> {
                Log.d(TAG, "execute: LOAD RATE")
                RateRepository.loadRublesToDollarRate().mapEvents(
                    eventMapper = { Event.Internal.RateLoaded(it) },
                    errorMapper = { error(it) }
                )
            }
        }

    }
}
