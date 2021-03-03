package com.example.bike.riders.feature.login.contracts


interface LoginInteractorOut {
    fun onloggedInUseradd()
    fun onloggedInPasswordError()

    fun noSuchUserFound()
    fun userValidatedSuccessfully(email: String, password: String)

    fun onloggedInEmailError()
}
