package com.example.bike.riders.feature.signup.interactor

import com.example.bike.riders.feature.signup.contracts.SignupInteractor
import com.example.bike.riders.feature.signup.contracts.SignupInteractorOut
import com.example.bike.riders.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignupInteractorImpl @Inject constructor() : SignupInteractor {
    private var signupInteractorout: SignupInteractorOut? = null

    @Inject
    lateinit var repository: UserRepository.Repo

    @Inject
    lateinit var saveRepo: UserRepository.Repo

    override fun setInteractorOut(interactorOut: SignupInteractorOut) {
        this.signupInteractorout = interactorOut
    }

    override fun addSignedUpUserData(email: String, password: String) {
        saveRepo.saveUser(email, password)
        signupInteractorout?.onSignUpUseradd()
    }

    override fun validateUserData(email: String, password: String) {
        signupInteractorout?.userValidatedSuccessfully(email, password)
        /*    repository.authenticate(username = username,
                    password = password,
                    listener = object : RepositoryListener<User> {
                        override fun onResult(data: User?) {
                            if (data == null) {
                                signupInteractorout?.noSuchUserFound()
                            } else {
                                //loginInteractorout?.onloggedInUseradd()
                                signupInteractorout?.userValidatedSuccessfully(data)
                            }
                        }

                        override fun onFailure(throwable: Throwable?) {
                            signupInteractorout?.userNotValidated()
                        }

                    })*/
    }
}