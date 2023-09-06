package com.example.rate_table_feature.data.model

import com.google.gson.annotations.SerializedName

data class RateResponse(
    @SerializedName("success") var success: Boolean = true,
    @SerializedName("timestamp") var timestamp: Int,
    @SerializedName("base") var base: String,
    @SerializedName("date") var date: String,
    @SerializedName("rates") var rates: Map<String, Double>
)
