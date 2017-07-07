package com.example.kotlinmvpespresso.di

import com.example.kotlinmvpespresso.ui.home.HomeActivity
import com.example.kotlinmvpespresso.ui.repositoriesList.RepositoriesListActivity
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

    fun inject(homeActivity: HomeActivity)

    fun inject(repositoryListActivity: RepositoriesListActivity)
}