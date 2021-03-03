package com.example.bike.riders.di.component

import android.app.Application
import com.example.bike.riders.MyApplication
import com.example.bike.riders.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    StorageModule::class,
    RepositoryModule::class,
    ActivitiesModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun storageModule(storageModule: StorageModule): Builder

        fun build(): ApplicationComponent
    }

    fun inject(appController: MyApplication)
}
