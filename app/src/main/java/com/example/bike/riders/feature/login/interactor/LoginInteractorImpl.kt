package com.example.bike.riders.feature.login.interactor

import com.example.bike.riders.feature.login.contracts.LoginInteractor
import com.example.bike.riders.feature.login.contracts.LoginInteractorOut
import com.example.bike.riders.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginInteractorImpl @Inject constructor() : LoginInteractor {
    private var loginInteractorout: LoginInteractorOut? = null

    @Inject
    lateinit var repository: UserRepository.Repo

   @Inject
   lateinit var saveRepo:UserRepository.Repo

    override fun setInteractorOut(interactorOut: LoginInteractorOut) {
        this.loginInteractorout = interactorOut
    }

    override fun addLoggedInUserData(email: String, password: String) {
        saveRepo.saveUser(email , password)
        loginInteractorout?.onloggedInUseradd()
    }

    override fun validateUserData(email: String, password: String) {
        if(email == "muhammadhamza@venturedive.com" && password == "123456"){
            loginInteractorout?.userValidatedSuccessfully(email, password)

        }else{
            if(email != "muhammadhamza@venturedive.com"){
                loginInteractorout?.onloggedInEmailError()
            }else{
                loginInteractorout?.onloggedInPasswordError()

            }
        }
    }

}