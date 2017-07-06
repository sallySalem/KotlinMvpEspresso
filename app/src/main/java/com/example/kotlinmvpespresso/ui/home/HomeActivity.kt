package com.example.kotlinmvpespresso.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.TestApplication
import com.example.kotlinmvpespresso.ui.base.BaseActivity
import com.example.kotlinmvpespresso.ui.repositoriesList.RepositoriesListActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */

class HomeActivity : BaseActivity(), HomeView {
    @Inject
    lateinit var homePresenter: HomePresenter

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun initView() {
        but_show_repositories.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                homePresenter.onShowRepositoriesClick(et_github_account_name.text.toString())
            }
        })
    }

    override fun navigateToRepositoriesList(gitHubName: String) {
        RepositoriesListActivity.open(this, gitHubName)
    }

    override fun showErrorMessage() {
        var errorSnackBar = Snackbar.make(coordinatorLayout, R.string.please_enter_github_name, Snackbar.LENGTH_LONG)
        errorSnackBar.view.setBackgroundColor(Color.RED)
        errorSnackBar.show()
    }

    override fun initializeDagger() {
        val app = application as TestApplication
        app.appComponent?.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = homePresenter
        homePresenter.view = this;
    }

    override var layoutId: Int = R.layout.activity_home
}