package com.example.rate_table_feature.data

import com.example.rate_table_feature.data.model.RateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {

    @GET("latest")
    suspend fun getRates(@Query("access_key") accessKey: String): RateResponse

}