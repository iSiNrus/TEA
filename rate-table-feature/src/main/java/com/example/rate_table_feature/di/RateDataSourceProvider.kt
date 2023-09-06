package com.example.rate_table_feature.di

import com.example.rate_table_feature.data.RatesApiDataSource
import javax.inject.Provider

class RateDataSourceProvider: Provider<RatesApiDataSource> {
    override fun get(): RatesApiDataSource {
        return RatesApiDataSource()
    }
}