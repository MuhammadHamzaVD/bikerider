package com.example.bike.riders.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.bike.riders.storage.Storage
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideSharePreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences(Storage.NAME, Context.MODE_PRIVATE)
    }

}