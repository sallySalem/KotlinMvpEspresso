package com.example.kotlinmvpespresso.ui.details

import android.os.Bundle
import android.os.Parcelable
import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.utils.Constants
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Sally on 19/11/2017.
 */
class DetailsPresenterTest {
    private var detailsPresenter: DetailsPresenter? = DetailsPresenter()

    val mockView: DetailsView? = mock(DetailsView::class.java)
    @Mock
    lateinit var mockBundle: Bundle
    @Mock
    lateinit var repositoryModel: RepositoryModel
    @Mock
    lateinit var ownerInfo: OwnerModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailsPresenter?.view = mockView
    }

    @Test
    fun testInitialize_Valid() {
        `when`<Parcelable>(mockBundle.getParcelable(Constants.KEY_REPOSITORY_MODEL)).thenReturn(repositoryModel)
        `when`(repositoryModel.ownerInfo).thenReturn(ownerInfo)

        detailsPresenter?.initialize(mockBundle)

        verify<DetailsView>(mockView).initRepositoryDetailsView(repositoryModel)
        verify<DetailsView>(mockView).hideProgressbar()
        verify<DetailsView>(mockView).hideEmptyDataView()
        verify<DetailsView>(mockView).initOwnerView(ownerInfo)
    }

    @Test
    fun testInitalize_InvalidExtra(){
        `when`<Parcelable>(mockBundle.getParcelable(Constants.KEY_REPOSITORY_MODEL)).thenReturn(null)

        detailsPresenter?.initialize(mockBundle)

        verify<DetailsView>(mockView, never()).initRepositoryDetailsView(repositoryModel)
        verify<DetailsView>(mockView, never()).hideProgressbar()
        verify<DetailsView>(mockView, never()).hideEmptyDataView()
        verify<DetailsView>(mockView, never()).initOwnerView(ownerInfo)
    }

    @Test
    fun testInitalize_InvalidOwnerInfo(){
        `when`<Parcelable>(mockBundle.getParcelable(Constants.KEY_REPOSITORY_MODEL)).thenReturn(repositoryModel)

        detailsPresenter?.initialize(mockBundle)

        verify<DetailsView>(mockView).initRepositoryDetailsView(repositoryModel)
        verify<DetailsView>(mockView, never()).hideProgressbar()
        verify<DetailsView>(mockView, never()).hideEmptyDataView()
        verify<DetailsView>(mockView, never()).initOwnerView(ownerInfo)
    }
}