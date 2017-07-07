package com.example.kotlinmvpespresso.ui.repositoriesList

import android.os.Bundle
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BasePresenter
import com.example.kotlinmvpespresso.useCase.RepositoryUseCase
import com.example.kotlinmvpespresso.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class RepositoriesListPresenter @Inject constructor(val repositoryUseCase: RepositoryUseCase): BasePresenter<RepositoriesListView>(){

    // var repositoryUseCase: RepositoryUseCase? = null
    var repositoriesList = ArrayList<RepositoryModel>()
    lateinit var gitHubAccountName: String


    override fun initialize(extras: Bundle?) {
        super.initialize(extras)

        if (extras != null) {
            gitHubAccountName = extras.getString(Constants.KEY_GIT_HUB_ACCOUNT_MODEL)
        }

        view?.initView(repositoriesList)
        getRepositoriesList()
    }

    fun onRepositoryListRefresh() {
        getRepositoriesList()
    }

    fun onItemClick(position: Int) {
        view?.navigateToRepositoryDetails(repositoriesList[position])
    }
    private fun getRepositoriesList() {
        //Call backend to get repositories list
        repositoryUseCase.getRepositoriesList(gitHubAccountName, repositoriesListCallback)
    }

    private val repositoriesListCallback = object : Callback<ArrayList<RepositoryModel>> {
        override fun onResponse(call: Call<ArrayList<RepositoryModel>>, response: retrofit2.Response<ArrayList<RepositoryModel>>) {
            view?.hideSwipeRefreshLayout()
            if (response.isSuccessful) {
                //handle successful case with response
                val responseResult = response.body()
                if (responseResult != null && responseResult.size > 0) {
                    repositoriesList.removeAll(repositoriesList)
                    repositoriesList.addAll(responseResult)
                    view?.loadRepositoriesList()
                } else {
                    //handle successful case with empty response
                    view?.hideRepositoriesList()
                }
            } else {
                view?.hideRepositoriesList()
            }
        }

        override fun onFailure(call: Call<ArrayList<RepositoryModel>>, t: Throwable) {
            //handle failure
            view?.hideSwipeRefreshLayout()
            view?.hideRepositoriesList()
            view?.showErrorMessage()
        }
    }
}