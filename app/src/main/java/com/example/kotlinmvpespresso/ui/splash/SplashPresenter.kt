package com.example.kotlinmvpespresso.ui.splash

import android.os.Handler
import com.example.kotlinmvpespresso.ui.base.BasePresenter
import com.example.kotlinmvpespresso.ui.base.BaseView
import com.example.kotlinmvpespresso.utils.Constants
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class SplashPresenter @Inject constructor() : BasePresenter<SplashView>() {
    override fun initialize(extras: android.os.Bundle?) {
        super.initialize(extras)
        Handler().postDelayed({ view?.navigateToHomeScreen() }, Constants.SPLASH_TIME)
    }
}