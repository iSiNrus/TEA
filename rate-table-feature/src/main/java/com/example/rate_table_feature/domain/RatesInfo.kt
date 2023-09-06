package com.example.rate_table_feature.domain

data class RatesInfo(
    var success: Boolean,
    var timestamp: Int,
    var base: String?,
    var date: String?,
    var rates: Map<String, Double>?
)