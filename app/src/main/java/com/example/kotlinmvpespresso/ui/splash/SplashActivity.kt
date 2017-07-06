package com.example.kotlinmvpespresso.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.TestApplication
import com.example.kotlinmvpespresso.ui.base.BaseActivity
import com.example.kotlinmvpespresso.ui.home.HomeActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter

    override fun navigateToHomeScreen() {
        HomeActivity.open(this)
        finish()
    }

    override fun initializeDagger() {
        val app = application as TestApplication
        app.appComponent?.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = splashPresenter
        splashPresenter.view = this
    }

    override var layoutId: Int = R.layout.activity_splash
}

