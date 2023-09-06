package com.example.rate_table_feature.data

import com.example.rate_table_feature.data.model.RateResponse
import toothpick.Toothpick
import javax.inject.Inject

class RatesApiDataSource {

    constructor() {
        val scope = Toothpick.openRootScope()
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var ratesService: RatesService

    private val access_key = "07b39ac7cfab6b6a34920ab6a8510caf"
    suspend fun getRates(): RateResponse {
        return ratesService.getRates(access_key)
    }

}
