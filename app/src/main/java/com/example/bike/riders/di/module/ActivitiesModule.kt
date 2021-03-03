package com.example.bike.riders.di.module

import com.example.bike.riders.feature.login.di.LoginActivityScope
import com.example.bike.riders.feature.login.di.LoginModule
import com.example.bike.riders.feature.login.view.LoginActivity

import com.example.bike.riders.feature.main.di.MainActivityScope
import com.example.bike.riders.feature.main.di.MainModule
import com.example.bike.riders.feature.main.view.MainActivity
import com.example.bike.riders.feature.maps.di.MapsActivityScope
import com.example.bike.riders.feature.maps.di.MapsModule
import com.example.bike.riders.feature.maps.view.MapsActivity

import com.example.bike.riders.feature.signup.di.SignupActivityScope
import com.example.bike.riders.feature.signup.di.SignupModule
import com.example.bike.riders.feature.signup.view.SignupActivity

import com.example.bike.riders.feature.splash.di.SplashActivityScope
import com.example.bike.riders.feature.splash.di.SplashModule
import com.example.bike.riders.feature.splash.view.SplashActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesModule {

    @SplashActivityScope
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    fun bindSplashActivity(): SplashActivity

    @MapsActivityScope
    @ContributesAndroidInjector(modules = [(MapsModule::class)])
    fun bindMapsActivity(): MapsActivity

    @MainActivityScope
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    fun bindMainActivity(): MainActivity

    @LoginActivityScope
    @ContributesAndroidInjector(modules = [(LoginModule::class)])
    fun bindLoginActivity(): LoginActivity

    @SignupActivityScope
    @ContributesAndroidInjector(modules = [(SignupModule::class)])
    fun bindSignupActivity(): SignupActivity
}