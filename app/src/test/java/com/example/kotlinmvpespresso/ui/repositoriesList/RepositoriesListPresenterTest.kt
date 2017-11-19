package com.example.kotlinmvpespresso.ui.repositoriesList

import android.os.Bundle
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.useCase.RepositoryUseCase
import com.example.kotlinmvpespresso.utils.Constants
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * Created by Sally on 19/11/2017.
 */
class RepositoriesListPresenterTest {
    lateinit var repositoriesListPresenter: RepositoriesListPresenter

    @Mock
    lateinit var repositoriesListView: RepositoriesListView
    @Mock
    lateinit var mockBundle: Bundle
    @Mock
    internal lateinit var mockCall:retrofit2.Call<*>
    @Mock
    internal lateinit var mockThrowable: Throwable
    @Mock
    lateinit var repositoryUseCase: RepositoryUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repositoriesListPresenter = RepositoriesListPresenter(repositoryUseCase)
        repositoriesListPresenter?.view = repositoriesListView

        `when`(mockBundle.getString(Constants.KEY_GIT_HUB_ACCOUNT_MODEL)).thenReturn("test")
    }

    @Test
    fun initialize_emptyList() {

//        Mockito.doAnswer(object : Answer<Any> {
//            @Throws(Throwable::class)
//            override fun answer(invocation: InvocationOnMock): Any? {
//                val callback = invocation.getArgument<*>(1)
//                val response = retrofit2.Response.success<ArrayList<RepositoryModel>>(repositoriesListModel)
//                callback.onResponse(mockCall, response)
//                return null
//            }
//        }).`when`(repositoryUseCase).getRepositoriesList(ArgumentMatchers.anyString(), ArgumentMatchers.any<Any>() as Callback<ArrayList<RepositoryModel>>)

        Mockito.doAnswer {
            val callback = it.arguments[0] as Callback<ArrayList<RepositoryModel>>
            val repositoriesListModel = ArrayList<RepositoryModel>();
            val response = retrofit2.Response.success<ArrayList<RepositoryModel>>(repositoriesListModel)
            callback.onResponse(mockCall as Call<ArrayList<RepositoryModel>>, response)
            null
        }.`when`(repositoryUseCase).getRepositoriesList(ArgumentMatchers.anyString(), ArgumentMatchers.any<Any>() as Callback<ArrayList<RepositoryModel>>)

        repositoriesListPresenter.initialize(mockBundle)
        verify<RepositoriesListView>(repositoriesListView).hideRepositoriesList()
    }
}