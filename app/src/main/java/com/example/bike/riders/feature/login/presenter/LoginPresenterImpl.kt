package com.example.bike.riders.feature.login.presenter

import android.util.Patterns
import com.example.bike.riders.feature.login.contracts.LoginInteractor
import com.example.bike.riders.feature.login.contracts.LoginInteractorOut
import com.example.bike.riders.feature.login.contracts.LoginPresenter
import com.example.bike.riders.feature.login.contracts.LoginView

class LoginPresenterImpl(val view: LoginView, val interactor: LoginInteractor) : LoginPresenter, LoginInteractorOut {
    init {
        interactor.setInteractorOut(this)
    }

    override fun checkValidation(email: String, password: String) {
        if (email.isEmpty()) {
            view.emptyEmailField()
        } else if (password.isEmpty()) {
            view.emptyPasswordField()
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6) {
            interactor.validateUserData(email, password)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.invalidateEmail()
        } else {
            view.invalidatePassword()
        }

    }

    override fun onloggedInUseradd() {
        view.navigateToMainScreen()
    }

    override fun onloggedInEmailError() {
        view.invalidateEmail()
    }

    override fun noSuchUserFound() {
        view.validatationError()
    }

    override fun userValidatedSuccessfully(email: String, password: String) {
        interactor.addLoggedInUserData(email, password)
    }

    override fun onloggedInPasswordError() {
        view.invalidatePassword()
    }

}