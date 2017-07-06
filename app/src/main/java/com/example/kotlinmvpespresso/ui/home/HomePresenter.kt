package com.example.kotlinmvpespresso.ui.home

import android.os.Bundle
import com.example.kotlinmvpespresso.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class HomePresenter @Inject constructor() : BasePresenter<HomeView>() {
    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        view?.initView()
    }

    fun onShowRepositoriesClick(gitHubAccount: String) {
        if(!gitHubAccount.isNullOrEmpty()){
            view?.navigateToRepositoriesList(gitHubAccount)
        }else{
            view?.showErrorMessage()
        }
    }
}