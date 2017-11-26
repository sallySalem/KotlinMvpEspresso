package com.example.kotlinmvpespresso.useCase

import com.example.kotlinmvpespresso.data.ApiRepository
import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import retrofit2.Callback
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
open class RepositoryUseCase @Inject
constructor(val repository: ApiRepository) {

    open fun getRepositoriesList(gitHubAccountName: String, repositoriesListCallback: Callback<ArrayList<RepositoryModel>>) {
        val callResponse = repository.getRepositoriesList(gitHubAccountName)
        callResponse.enqueue(repositoriesListCallback)
    }

    fun getTest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
