package com.example.bike.riders.feature.main.presenter

import com.example.bike.riders.feature.main.api.Network
import com.example.bike.riders.feature.main.contracts.MainInteractor
import com.example.bike.riders.feature.main.contracts.MainInteractorOut
import com.example.bike.riders.feature.main.contracts.MainPresenter
import com.example.bike.riders.feature.main.contracts.MainView
import retrofit2.Response

class MainPresenterImpl(val view: MainView, val interactor: MainInteractor) : MainPresenter, MainInteractorOut {
    init {
        interactor.setInteractorOut(this)
    }

    override fun checkLoggedOutUser() {
        interactor.loadLoggedOutUser()
    }

    override fun checkFetchData() {
        interactor.fetchApiData()
    }


    override fun onLoggedOutUser() {
        view.navigateToHomeScreen()
    }

    override fun passData(response: Response<Network>) {
        if(response == null || !response.isSuccessful){
            val message = "Empty Data"
            view.displayError(message)
        }else{
            val networks = response.body()
            networks?.bikes?.let { view.displayData(it) }
        }
    }

    override fun errorData(t: Throwable) {
        view.displayError(t.localizedMessage)
    }

}