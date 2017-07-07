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
class RepositoryUseCase @Inject
constructor(private val repository: ApiRepository) {

    fun getRepositoriesList(gitHubAccountName: String, repositoriesListCallback: Callback<ArrayList<RepositoryModel>>) {
        val callResponse = repository.getRepositoriesList(gitHubAccountName)
        callResponse.enqueue(repositoriesListCallback)
    }

    fun getRepositoryOwnerDetails(loginName: String, repositoryOwnerCallback: Callback<OwnerModel>) {
        val callResponse = repository.getRepositoryOwner(loginName)
        callResponse.enqueue(repositoryOwnerCallback)
    }
}
