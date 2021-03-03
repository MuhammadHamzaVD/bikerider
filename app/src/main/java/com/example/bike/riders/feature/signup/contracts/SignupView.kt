package com.example.bike.riders.feature.signup.contracts

interface SignupView {
    fun navigateToMainScreen()
    fun validatationError()
    fun invalidateEmail()
    fun invalidatePassword()
    fun invalidateConfirm()
    fun invalidateUsername()

    fun emptyEmailField()
    fun emptyPasswordField()
    fun emptyConfirmField()
    fun emptyAgeField()
    fun emptyUsernameField()
}