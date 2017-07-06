package com.example.kotlinmvpespresso

import android.app.Application
import com.example.kotlinmvpespresso.di.AppComponent
import com.example.kotlinmvpespresso.di.DaggerAppComponent

/**
 * Created by sally on 7/7/17.
 */
class TestApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}