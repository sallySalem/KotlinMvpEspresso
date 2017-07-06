package com.example.kotlinmvpespresso.ui.home

import com.example.kotlinmvpespresso.ui.base.BaseView

/**
 * Created by sally on 7/7/17.
 */
interface HomeView : BaseView {
    fun navigateToRepositoriesList(gitHubName: String)
    fun initView()
    fun showErrorMessage()
}