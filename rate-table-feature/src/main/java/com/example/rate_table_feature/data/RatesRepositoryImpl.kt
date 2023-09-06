package com.example.rate_table_feature.data

import com.example.rate_table_feature.domain.RatesInfo
import com.example.rate_table_feature.domain.RatesRepository
import toothpick.Toothpick
import javax.inject.Inject

class RatesRepositoryImpl : RatesRepository {
    constructor() {
        val scope = Toothpick.openRootScope()
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var ratesDataSource: RatesApiDataSource
    override suspend fun getRates(): RatesInfo {
        return ratesDataSource.getRates().toRateInfo()
    }
}