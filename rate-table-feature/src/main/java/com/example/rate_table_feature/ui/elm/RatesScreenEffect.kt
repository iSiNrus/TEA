package com.example.rate_table_feature.ui.elm

sealed class RatesScreenEffect {

    class ShowLoadErrorMessage(val message: String): RatesScreenEffect()

}
