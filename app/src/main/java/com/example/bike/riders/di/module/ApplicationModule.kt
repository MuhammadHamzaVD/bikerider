package com.example.bike.riders.di.module

import android.app.Application
import com.example.bike.riders.MyApplication
import com.example.bike.riders.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val myApplication: MyApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return myApplication
    }

}