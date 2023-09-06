package com.example.rate_table_feature.di

import com.example.rate_table_feature.data.RateClient
import com.example.rate_table_feature.data.RatesApiDataSource
import com.example.rate_table_feature.data.RatesService
import com.example.rate_table_feature.domain.RatesInteractor
import com.example.rate_table_feature.domain.RatesRepository
import toothpick.config.Module
import toothpick.ktp.binding.bind
import vivid.money.elmslie.core.store.Store

class RepositoryModule : Module {

    constructor() {
        val service = RateClient.getService()
        bind(RatesService::class).toInstance(service)

        bind(RatesApiDataSource::class).toProviderInstance(RateDataSourceProvider())
        bind(RatesRepository::class).toProviderInstance(RatesRepositoryProvider())
        bind(Store::class).toProviderInstance(RateStoreProvider())
        bind(RatesInteractor::class).toProviderInstance(RateInteractorProvider())


    }

}