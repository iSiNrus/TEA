package com.example.rate_table_feature.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RateClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://data.fixer.io/api/"
    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getService(): RatesService {
        return getClient().create(RatesService::class.java)
    }
}