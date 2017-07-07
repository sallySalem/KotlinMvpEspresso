package com.example.kotlinmvpespresso.data.service

import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList

/**
 * Created by sally on 7/7/17.
 */
interface RepositoriesService {

    @GET("users/{gitHubAccountName}/repos")
    fun getRepositoriesList(@Path("gitHubAccountName") gitHubAccountName: String): Call<ArrayList<RepositoryModel>>

    @GET("users/{loginName}")
    fun getRepositoryOwner(@Path("loginName") loginName: String): Call<OwnerModel>
}