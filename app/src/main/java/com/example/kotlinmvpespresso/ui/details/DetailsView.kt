package com.example.kotlinmvpespresso.ui.details

import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BaseView

/**
 * Created by sally on 7/7/17.
 */
interface DetailsView : BaseView {
    fun initRepositoryDetailsView(repositoryModel: RepositoryModel)

    fun initOwnerView(ownerInfo: OwnerModel)

    fun hideProgressbar()

    fun hideEmptyDataView()

    fun showEmptyDataView()

    fun showErrorMessage()
}