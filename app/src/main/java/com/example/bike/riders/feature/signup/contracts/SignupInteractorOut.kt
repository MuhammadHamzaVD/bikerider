package com.example.bike.riders.feature.signup.contracts

interface SignupInteractorOut {
    fun onSignUpUseradd()
    fun onSignUpUserError()

    fun noSuchUserFound()
    fun userValidatedSuccessfully(email: String, password: String)

    fun userNotValidated()
}