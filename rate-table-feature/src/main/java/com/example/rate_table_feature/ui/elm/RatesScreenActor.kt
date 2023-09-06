package com.example.rate_table_feature.ui.elm

import com.example.rate_table_feature.domain.RatesInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import toothpick.Toothpick
import vivid.money.elmslie.core.store.Actor
import javax.inject.Inject

class RatesScreenActor : Actor<RatesScreenCommand, RatesScreenEvent> {

    constructor() : super() {
        val scope = Toothpick.openRootScope()
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var ratesInteractor: RatesInteractor
    override fun execute(command: RatesScreenCommand): Flow<RatesScreenEvent> {
        return when (command) {
            is RatesScreenCommand.LoadRates -> {
                flow {
                    val rates = ratesInteractor.getRates()
                    emit(rates)
                }.mapEvents(
                    {
                        RatesScreenEvent.Internal.RatesLoaded(it)
                    },
                    {
                        RatesScreenEvent.Internal.LoadError(it)
                    }
                )
            }
        }
    }
}