package com.example.rate_table_feature.ui.elm

data class RatesScreenState(
    val isLoading : Boolean = false,
    val rates: Map<String, Double?>? = emptyMap(),
    val base: String = "None"
)
