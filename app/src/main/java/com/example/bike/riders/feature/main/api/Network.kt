package com.example.bike.riders.feature.main.api

import com.google.gson.annotations.SerializedName

data class Network (@SerializedName("networks")  val bikes : ArrayList<Bike>)