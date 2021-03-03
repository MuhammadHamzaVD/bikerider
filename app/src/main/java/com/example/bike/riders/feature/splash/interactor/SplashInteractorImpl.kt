package com.example.bike.riders.feature.splash.interactor

import com.example.bike.riders.feature.splash.contracts.SplashInteractor
import com.example.bike.riders.feature.splash.contracts.SplashInteractorOut
import com.example.bike.riders.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashInteractorImpl @Inject constructor() : SplashInteractor {
    private var interactorOut: SplashInteractorOut? = null

    @Inject
    lateinit var repository: UserRepository.Repo

    override fun setInteractorOut(interactorOut: SplashInteractorOut) {
        this.interactorOut = interactorOut
    }

    override fun loadLoggedInUserData() {
        if(repository.getUser() != null){
           interactorOut?.onLoggedInUserFound()
        } else {
            interactorOut?.onLoggedInUserNotFound()
        }
    }
}