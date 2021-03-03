package com.example.bike.riders.feature.main.interactor

import android.util.Log
import com.example.bike.riders.feature.main.api.Network
import com.example.bike.riders.feature.main.contracts.MainInteractor
import com.example.bike.riders.feature.main.contracts.MainInteractorOut
import com.example.bike.riders.feature.main.view.NetworkServices
import com.example.bike.riders.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainInteractorImpl @Inject constructor() : MainInteractor{
    private var mainInteractorOut: MainInteractorOut? = null

    @Inject
    lateinit var repository: UserRepository.Repo

    override fun setInteractorOut(interactorOut: MainInteractorOut) {
        this.mainInteractorOut = interactorOut
    }

    override fun loadLoggedOutUser() {
        repository.deleteUser()
        mainInteractorOut?.onLoggedOutUser()
    }


    override fun fetchApiData() {
        val networks = NetworkServices.networkInstance.getBikeDetail()
        networks.enqueue(object : Callback<Network> {
            override fun onFailure(call: Call<Network>, t: Throwable) {
                Log.d("BikesDetail", "Error in Fetching Data", t)
                mainInteractorOut?.errorData(t)
            }

            override fun onResponse(call: Call<Network>, response: Response<Network>) {
                val networks = response.body()
                    Log.d("BikeDetail", networks.toString())
                    mainInteractorOut?.passData(response)
            }
        })
    }

}