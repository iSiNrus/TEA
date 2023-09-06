package com.example.rate_table_feature.domain

interface RatesRepository {

    suspend fun getRates(): RatesInfo

}