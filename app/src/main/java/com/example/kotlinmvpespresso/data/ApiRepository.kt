package com.example.kotlinmvpespresso.data

import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.data.service.RepositoriesService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class ApiRepository @Inject
constructor(gson: Gson) {
    private val service: Retrofit

    init {
        this.service = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun getRepositoriesList(gitHubAccountName: String): Call<ArrayList<RepositoryModel>> {
        val repositoriesService = service.create(RepositoriesService::class.java)
        return repositoriesService.getRepositoriesList(gitHubAccountName)
    }

    fun getRepositoryOwner(loginName: String): Call<OwnerModel> {
        val repositoriesService = service.create(RepositoriesService::class.java)
        return repositoriesService.getRepositoryOwner(loginName)
    }

    companion object {
        val BASE_URL = "https://api.github.com/"
    }
}
