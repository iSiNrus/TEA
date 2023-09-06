package com.example.rate_table_feature.data

import com.example.rate_table_feature.data.model.RateResponse
import com.example.rate_table_feature.domain.RatesInfo

fun RateResponse.toRateInfo(): RatesInfo {
    return RatesInfo(
        success = success,
        timestamp = timestamp,
        base = base,
        date = date,
        rates = rates
    )
}