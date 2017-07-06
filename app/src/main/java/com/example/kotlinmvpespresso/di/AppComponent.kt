package com.example.kotlinmvpespresso.di

import com.example.kotlinmvpespresso.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sally on 7/7/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(splashActivity: SplashActivity)
}