package com.example.bike.riders.feature.main.contracts
import com.example.bike.riders.feature.main.api.Network
import retrofit2.Response


interface MainInteractorOut {
    fun onLoggedOutUser()
    fun passData(response: Response<Network>)
    fun errorData(t: Throwable)
}