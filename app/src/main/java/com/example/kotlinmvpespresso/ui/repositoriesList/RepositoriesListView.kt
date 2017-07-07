package com.example.kotlinmvpespresso.ui.repositoriesList

import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BaseView
import java.util.ArrayList

/**
 * Created by sally on 7/7/17.
 */
interface RepositoriesListView : BaseView {
    fun initView(repositoriesList: ArrayList<RepositoryModel>)

    fun loadRepositoriesList()

    fun hideSwipeRefreshLayout()

    fun hideRepositoriesList()

    fun showErrorMessage()

    fun navigateToRepositoryDetails(repositoryModel: RepositoryModel)
}