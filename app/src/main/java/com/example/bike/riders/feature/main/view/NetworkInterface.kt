package com.example.bike.riders.feature.main.view

import com.example.bike.riders.feature.main.api.Network
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val Base_URL = "http://api.citybik.es/v2/"

interface NetworkInterface {

    @GET("networks")
    fun getBikeDetail(): Observable<Network>
}

object NetworkServices {
    val networkInstance: NetworkInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkInstance = retrofit.create(NetworkInterface::class.java)
    }
}