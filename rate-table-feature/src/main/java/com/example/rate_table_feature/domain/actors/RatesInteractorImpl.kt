package com.example.rate_table_feature.domain.actors

import com.example.rate_table_feature.domain.RatesInfo
import com.example.rate_table_feature.domain.RatesInteractor
import com.example.rate_table_feature.domain.RatesRepository
import toothpick.Toothpick
import javax.inject.Inject

class RatesInteractorImpl : RatesInteractor {
    constructor() {
        val scope = Toothpick.openRootScope()
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var ratesRepository: RatesRepository
    override suspend fun getRates(): RatesInfo {
        return ratesRepository.getRates()
    }

}