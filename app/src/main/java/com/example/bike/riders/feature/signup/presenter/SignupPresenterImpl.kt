package com.example.bike.riders.feature.signup.presenter

import android.util.Patterns
import com.example.bike.riders.feature.signup.contracts.SignupInteractor
import com.example.bike.riders.feature.signup.contracts.SignupInteractorOut
import com.example.bike.riders.feature.signup.contracts.SignupPresenter
import com.example.bike.riders.feature.signup.contracts.SignupView
import java.util.regex.Pattern


class SignupPresenterImpl(val view: SignupView, val interactor: SignupInteractor) : SignupPresenter, SignupInteractorOut {
    init {
        interactor.setInteractorOut(this)
    }

    override fun checkValidation(email: String, password: String, confirm: String, username: String, age: String) {

        val p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(username)
        val b = m.find()

        if (email.isEmpty()) {
            view.emptyEmailField()
        } else if (username.isEmpty()) {
            view.emptyUsernameField()
        } else if (password.isEmpty()) {
            view.emptyPasswordField()
        } else if (confirm.isEmpty()) {
            view.emptyConfirmField()
        } else if (age.isEmpty()) {
            view.emptyAgeField()
        } else if (b) {
            view.invalidateUsername()
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6 && confirm == password) {
            interactor.validateUserData(email, password)
        } else if (confirm != password) {
            view.invalidateConfirm()
        } else if (password.length < 6) {
            view.invalidatePassword()
        } else {
            view.invalidateEmail()
        }

    }

    override fun onSignUpUseradd() {
        view.navigateToMainScreen()
    }

    override fun onSignUpUserError() {
        view.validatationError()
    }

    override fun noSuchUserFound() {
        view.validatationError()
    }

    override fun userValidatedSuccessfully(email: String, password: String) {
        interactor.addSignedUpUserData(email, password)
    }

    override fun userNotValidated() {
        view.validatationError()
    }
}