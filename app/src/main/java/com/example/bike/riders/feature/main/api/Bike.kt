package com.example.bike.riders.feature.main.api

import com.google.gson.annotations.SerializedName

data class Bike(@SerializedName("id") val id: String, @SerializedName("name") val name: String, @SerializedName("location") val location: Location)