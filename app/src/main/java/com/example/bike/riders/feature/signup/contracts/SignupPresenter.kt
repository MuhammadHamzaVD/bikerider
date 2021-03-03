package com.example.bike.riders.feature.signup.contracts

interface SignupPresenter {
    fun checkValidation(email: String, password: String, confirm: String, username: String, age: String)
}