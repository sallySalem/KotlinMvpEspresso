package com.example.kotlinmvpespresso.di

import android.os.Handler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sally on 7/7/17.
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gson = GsonBuilder().create()
        return gson
    }

    @Provides
    fun provideHandler(): Handler {
        return Handler()
    }
}