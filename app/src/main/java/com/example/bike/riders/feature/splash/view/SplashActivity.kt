package com.example.bike.riders.feature.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.bike.riders.feature.splash.contracts.SplashInteractor
import com.example.bike.riders.feature.splash.contracts.SplashPresenter
import com.example.bike.riders.feature.splash.contracts.SplashView
import com.example.bike.riders.feature.splash.presenter.SplashPresenterImpl
import com.example.bike.riders.feature.login.view.LoginActivity
import com.example.bike.riders.feature.main.view.MainActivity
import dagger.android.DaggerActivity
import javax.inject.Inject

class SplashActivity : DaggerActivity(), SplashView {

    private lateinit var presenter: SplashPresenter
    @Inject
    lateinit var splashInteractor: SplashInteractor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SplashPresenterImpl(this, splashInteractor)

        Handler().postDelayed({ presenter.checkLoggedInUser() }, 2000)
    }


    override fun navigateToHomeScreen() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun navigateToLoginScreen() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }
}
