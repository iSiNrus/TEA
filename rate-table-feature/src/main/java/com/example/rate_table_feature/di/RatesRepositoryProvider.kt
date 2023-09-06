package com.example.rate_table_feature.di

import com.example.rate_table_feature.data.RatesRepositoryImpl
import com.example.rate_table_feature.domain.RatesRepository
import javax.inject.Provider

class RatesRepositoryProvider : Provider<RatesRepository> {

    override fun get(): RatesRepository {
        return RatesRepositoryImpl()
    }
}