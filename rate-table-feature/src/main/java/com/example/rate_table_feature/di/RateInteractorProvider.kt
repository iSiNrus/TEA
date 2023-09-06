package com.example.rate_table_feature.di

import com.example.rate_table_feature.domain.RatesInteractor
import com.example.rate_table_feature.domain.actors.RatesInteractorImpl
import javax.inject.Provider

class RateInteractorProvider : Provider<RatesInteractor> {
    override fun get(): RatesInteractor {
        return RatesInteractorImpl()
    }

}