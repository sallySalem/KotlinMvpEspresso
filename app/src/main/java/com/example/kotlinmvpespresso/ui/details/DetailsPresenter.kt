package com.example.kotlinmvpespresso.ui.details

import android.os.Bundle
import android.os.Parcelable
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BasePresenter
import com.example.kotlinmvpespresso.utils.Constants
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class DetailsPresenter @Inject constructor(): BasePresenter<DetailsView>() {
    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        if (extras != null) {
            //init details view
            val repositoryModel = extras.getParcelable<Parcelable>(Constants.KEY_REPOSITORY_MODEL) as RepositoryModel?
            repositoryModel?.let {
                view?.initRepositoryDetailsView(it)

                var ownerInfo = it.ownerInfo
                ownerInfo?.let {
                    view?.hideProgressbar()
                    view?.hideEmptyDataView()
                    view?.initOwnerView(ownerInfo)
                }
            }
        }
    }
}