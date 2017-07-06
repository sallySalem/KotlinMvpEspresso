package com.example.kotlinmvpespresso.ui.splash

import android.support.test.espresso.Espresso
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.kotlinmvpespresso.ui.home.HomeActivity
import com.example.kotlinmvpespresso.utils.Constants
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent

/**
 * Created by sally on 7/7/17.
 */
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {
    var idlingResource: SplashIdlingResource? = null

    @JvmField @Rule
    var splashIntentTestRule = IntentsTestRule<SplashActivity>(SplashActivity::class.java)

    @Before
    fun setUp() {
        idlingResource = SplashIdlingResource(Constants.SPLASH_TIME) // mActivityRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource)
    }

    @After
    fun tearDown() {
        Espresso.unregisterIdlingResources(idlingResource)
    }

    @Test
    fun navigateToHomeScreen() {
        intended(hasComponent(HomeActivity::class.java.getName()))
    }
}