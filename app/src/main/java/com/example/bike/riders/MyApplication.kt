package com.example.bike.riders

import android.app.Activity
import android.app.Application
import com.example.bike.riders.di.component.DaggerApplicationComponent
import com.example.bike.riders.di.module.StorageModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .storageModule(StorageModule())
                .build()
                .inject(this)
    }
}
