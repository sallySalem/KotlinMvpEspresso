package com.example.kotlinmvpespresso.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.repositoriesList.RepositoriesListActivity
import com.example.kotlinmvpespresso.utils.Constants

/**
 * Created by sally on 7/7/17.
 */
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
    companion object {
        fun open(activity: Activity, repositoryModel: RepositoryModel) {
            val repositoryDetailsIntent = Intent(activity, DetailsActivity::class.java)
            repositoryDetailsIntent.putExtra(Constants.KEY_REPOSITORY_MODEL, repositoryModel)
            activity.startActivity(repositoryDetailsIntent)
        }
    }
}