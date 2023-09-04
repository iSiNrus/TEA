package ru.barsik.tea.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object RateRepository {

    private var rate: Float? = null

    fun getRateRublesToDollar() : Float {
        return rate ?: 0f
    }

    fun loadRublesToDollarRate() : Flow<Float> = flow {
        delay(2000)
        rate = 0.010f
        emit(rate!!)
    }

    fun getRateDollarToRuble() : Float {
        return 96.00f
    }
}