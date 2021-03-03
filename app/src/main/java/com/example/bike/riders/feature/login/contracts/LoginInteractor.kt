package com.example.bike.riders.feature.login.contracts

interface LoginInteractor {
    fun setInteractorOut(interactorOut: LoginInteractorOut)

    fun addLoggedInUserData(email: String, password: String)

    fun validateUserData(email: String, password: String)
}