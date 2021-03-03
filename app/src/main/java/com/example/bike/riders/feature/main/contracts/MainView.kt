package com.example.bike.riders.feature.main.contracts

import com.example.bike.riders.feature.main.api.Bike

interface MainView {
    fun navigateToHomeScreen()
    fun displayData(arrayList: ArrayList<Bike>)
    fun displayError(message: String)
}