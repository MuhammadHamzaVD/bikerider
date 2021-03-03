package com.example.bike.riders.feature.signup.contracts

interface SignupInteractor {
    fun setInteractorOut(interactorOut: SignupInteractorOut)

    fun addSignedUpUserData(email: String , password: String)

    fun validateUserData(email: String, password: String)
}