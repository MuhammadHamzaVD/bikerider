package com.example.bike.riders.feature.splash.presenter

import com.example.bike.riders.feature.splash.contracts.SplashInteractor
import com.example.bike.riders.feature.splash.contracts.SplashInteractorOut
import com.example.bike.riders.feature.splash.contracts.SplashPresenter
import com.example.bike.riders.feature.splash.contracts.SplashView

class SplashPresenterImpl(val view: SplashView, val interactor: SplashInteractor) : SplashPresenter, SplashInteractorOut {
    init {
        interactor.setInteractorOut(this)
    }

    override fun checkLoggedInUser() {
        interactor.loadLoggedInUserData()
    }

    override fun onLoggedInUserFound() {
        view.navigateToHomeScreen()
    }

    override fun onLoggedInUserNotFound() {
        view.navigateToLoginScreen()
    }
}