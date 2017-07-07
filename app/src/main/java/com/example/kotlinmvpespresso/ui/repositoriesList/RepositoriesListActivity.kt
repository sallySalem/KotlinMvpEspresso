package com.example.kotlinmvpespresso.ui.repositoriesList

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.TestApplication
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BaseActivity
import com.example.kotlinmvpespresso.ui.details.DetailsActivity
import com.example.kotlinmvpespresso.utils.Constants
import kotlinx.android.synthetic.main.activity_repositories_list.*
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class RepositoriesListActivity : BaseActivity(), RepositoriesListView {

    @Inject
    lateinit var repositoriesListPresenter: RepositoriesListPresenter

    private var repositoryAdapter: RepositoriesListAdapter? = null


    override fun initView(repositoriesList: ArrayList<RepositoryModel>) {
        //init swipeRefreshLayout
        swipe_container.setColorSchemeColors(
                ContextCompat.getColor(this, android.R.color.holo_green_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_dark),
                ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        swipe_container.setRefreshing(true)
        swipe_container.setOnRefreshListener(pullToRefreshListener)

        //init recyclerView
        repositoryAdapter = RepositoriesListAdapter(repositoriesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_repositories_list.layoutManager = linearLayoutManager
        rv_repositories_list.adapter = repositoryAdapter
    }

    override fun loadRepositoriesList() {
        tv_empty_data.visibility = View.GONE
        rv_repositories_list.visibility = View.VISIBLE
        repositoryAdapter?.notifyDataSetChanged()
    }

    override fun hideSwipeRefreshLayout() {
        swipe_container.isRefreshing = false
    }

    override fun hideRepositoriesList() {
        rv_repositories_list.visibility = View.GONE
        tv_empty_data.visibility = View.VISIBLE
    }

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.mesg_server_error, Toast.LENGTH_LONG).show()
    }

    override fun navigateToRepositoryDetails(repositoryModel: RepositoryModel) {
        DetailsActivity.open(this, repositoryModel)
    }

    companion object {
        fun open(activity: Activity, githubAccount: String) {
            val repositoryDetailsIntent = Intent(activity, RepositoriesListActivity::class.java)
            repositoryDetailsIntent.putExtra(Constants.KEY_GIT_HUB_ACCOUNT_MODEL, githubAccount)
            activity.startActivity(repositoryDetailsIntent)
        }
    }

    override fun initializeDagger() {
        val app = application as TestApplication
        app.appComponent?.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = repositoriesListPresenter
        repositoriesListPresenter.view = this;
    }

    override var layoutId: Int = R.layout.activity_repositories_list

    private val pullToRefreshListener = SwipeRefreshLayout.OnRefreshListener { repositoriesListPresenter.onRepositoryListRefresh() }

    /*
    Listener to handle RecyclerView item select
     */
    private val itemClickListener = object : OnItemSelectedListener {
        override fun onClick(view: View, position: Int) {
            repositoriesListPresenter.onItemClick(position)
        }
    }
}