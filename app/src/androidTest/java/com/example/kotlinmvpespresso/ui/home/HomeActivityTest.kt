package com.example.kotlinmvpespresso.ui.home

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.ui.repositoriesList.RepositoriesListActivity
import com.example.kotlinmvpespresso.utils.Constants
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by sally on 7/7/17.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    val mActivityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, true, false)
    var gitHubAccountTest: String = "sallysalem"

    @Before
    fun setUp() {
        mActivityRule.launchActivity(null)
        Intents.init();
    }

    @After
    fun tearDown() {
        Intents.release();
    }

    @Test
    fun initUIDesignComponent() {

        val etGithubAccountName = onView(withId(R.id.et_github_account_name))
        etGithubAccountName.check(matches(isDisplayed()))
        etGithubAccountName.check(matches(isEnabled()))
        etGithubAccountName.check(matches(withText("")))
        etGithubAccountName.check(matches(withHint(R.string.please_enter_github_name)))

        val btnShowRepositories = onView(withId(R.id.but_show_repositories))
        btnShowRepositories.check(matches(isDisplayed()))
        btnShowRepositories.check(matches(withText(R.string.show_repositories_list)))
    }

    @Test
    fun enterGitHubAccountValue_invalid() { //empty value
        onView(allOf(withId(R.id.et_github_account_name), isDisplayed())).perform(clearText())

        onView(allOf(withId(R.id.but_show_repositories), withText(R.string.show_repositories_list), isDisplayed())).perform(click())

        onView(allOf(withId(R.id.snackbar_text), withText(R.string.please_enter_github_name))).check(matches(isDisplayed()));
    }

    @Test
    fun enterGitHubAccountValue_valid() {

        onView(allOf(withId(R.id.et_github_account_name), isDisplayed())).perform(typeText(gitHubAccountTest))

        onView(allOf(withId(R.id.but_show_repositories), withText("Show Repositories List"), isDisplayed())).perform(click())

        onView(allOf(withId(R.id.snackbar_text), withText(R.string.please_enter_github_name))).check(doesNotExist());
    }

    @Test
    fun navigateToRepositoriesListScreen() {

        onView(allOf(withId(R.id.et_github_account_name), isDisplayed())).perform(typeText(gitHubAccountTest))

        onView(allOf(withId(R.id.but_show_repositories), withText(R.string.show_repositories_list), isDisplayed())).perform(click())

        Intents.intended(IntentMatchers.hasComponent(RepositoriesListActivity::class.java!!.getName()))

        Intents.intended(IntentMatchers.hasExtra(Constants.KEY_GIT_HUB_ACCOUNT_MODEL, gitHubAccountTest));
    }
}