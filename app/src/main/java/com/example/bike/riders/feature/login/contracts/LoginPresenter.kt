package com.example.bike.riders.feature.login.contracts

interface LoginPresenter {
    fun checkValidation(email: String, password: String)
}