package com.example.kotlinmvpespresso.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.TestApplication
import com.example.kotlinmvpespresso.data.model.OwnerModel
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import com.example.kotlinmvpespresso.ui.base.BaseActivity
import com.example.kotlinmvpespresso.ui.repositoriesList.RepositoriesListActivity
import com.example.kotlinmvpespresso.utils.Constants
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by sally on 7/7/17.
 */
class DetailsActivity : BaseActivity(), DetailsView {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    lateinit var tvRepositoryName: TextView
    lateinit var tvRepositoryDescription: TextView
    lateinit var tvRepositoryLanguage: TextView

    lateinit var ivOwnerAvatar: ImageView
    lateinit var tvOwnerName: TextView
    lateinit var tvOwnerBlog: TextView
    lateinit var pbOwnerData: ProgressBar
    lateinit var tvEmptyData: TextView

    companion object {
        fun open(activity: Activity, repositoryModel: RepositoryModel) {
            val repositoryDetailsIntent = Intent(activity, DetailsActivity::class.java)
            repositoryDetailsIntent.putExtra(Constants.KEY_REPOSITORY_MODEL, repositoryModel)
            activity.startActivity(repositoryDetailsIntent)
        }
    }

    override fun initRepositoryDetailsView(repositoryModel: RepositoryModel) {
        //init repository info
        //Here only show how to use `findViewById` personally i totally recommend to use KotlinAndroidExtensions
        tvRepositoryName = findViewById(R.id.tv_repository_name_details) as TextView
        tvRepositoryDescription = findViewById(R.id.tv_repository_description_details) as TextView
        tvRepositoryLanguage = findViewById(R.id.tv_repository_language) as TextView

        ivOwnerAvatar = findViewById(R.id.iv_owner_avatar) as ImageView
        tvOwnerName = findViewById(R.id.tv_owner_name) as TextView
        tvOwnerBlog = findViewById(R.id.tv_owner_blog) as TextView
        pbOwnerData = findViewById(R.id.pb_owner_data) as ProgressBar
        tvEmptyData = findViewById(R.id.tv_empty_data) as TextView

        tvRepositoryName.text = repositoryModel.name
        tvRepositoryDescription.text = repositoryModel.description
        tvRepositoryLanguage.text = repositoryModel.language
    }

    override fun initOwnerView(ownerInfo: OwnerModel) {
        //init owner info
        Picasso.with(this).load(ownerInfo.avatarUrl).placeholder(R.mipmap.ic_launcher_round).into(ivOwnerAvatar)
        tvOwnerName.text = ownerInfo.login
        tvOwnerBlog.text = ownerInfo.ownerBlog
    }

    override fun hideProgressbar() {
        pbOwnerData.visibility = View.GONE
    }

    override fun hideEmptyDataView() {
        tvEmptyData.visibility = View.GONE
    }

    override fun showEmptyDataView() {
        tvEmptyData.visibility = View.VISIBLE
    }

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.mesg_server_error, Toast.LENGTH_LONG).show()
    }

    override fun initializeDagger() {
        val app = application as TestApplication
        app.appComponent?.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = detailsPresenter
        detailsPresenter.view = this;
    }

    override var layoutId: Int = R.layout.activity_details
}