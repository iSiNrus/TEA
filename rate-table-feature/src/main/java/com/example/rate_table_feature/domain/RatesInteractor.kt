package com.example.rate_table_feature.domain

interface RatesInteractor {

    suspend fun getRates(): RatesInfo

}