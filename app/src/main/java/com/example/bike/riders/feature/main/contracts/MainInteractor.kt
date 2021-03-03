package com.example.bike.riders.feature.main.contracts

interface MainInteractor {
    fun setInteractorOut(interactorOut: MainInteractorOut)
    fun loadLoggedOutUser()
    fun fetchApiData()
}