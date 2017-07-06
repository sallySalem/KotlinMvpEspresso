package com.example.kotlinmvpespresso.ui.repositoriesList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.ui.home.HomeActivity
import com.example.kotlinmvpespresso.utils.Constants

/**
 * Created by sally on 7/7/17.
 */
class RepositoriesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    companion object {
        fun open(activity: Activity, githubAccount: String) {
            val repositoryDetailsIntent = Intent(activity, RepositoriesListActivity::class.java)
            repositoryDetailsIntent.putExtra(Constants.KEY_GIT_HUB_ACCOUNT_MODEL, githubAccount)
            activity.startActivity(repositoryDetailsIntent)
        }
    }
}