package com.example.bike.riders.feature.login.contracts

interface LoginView {
    fun navigateToMainScreen()
    fun validatationError()
    fun invalidateEmail()
    fun invalidatePassword()


    fun emptyEmailField()
    fun emptyPasswordField()
}