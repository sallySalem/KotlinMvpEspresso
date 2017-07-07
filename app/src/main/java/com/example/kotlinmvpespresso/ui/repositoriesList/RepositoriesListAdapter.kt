package com.example.kotlinmvpespresso.ui.repositoriesList

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinmvpespresso.R
import com.example.kotlinmvpespresso.data.model.RepositoryModel
import kotlinx.android.synthetic.main.item_repository.view.*
import java.util.ArrayList

/**
 * Created by sally on 7/7/17.
 */
class RepositoriesListAdapter(private val repositoryList: ArrayList<RepositoryModel>, val itemClickListener: OnItemSelectedListener) : RecyclerView.Adapter<RepositoriesListAdapter.RepositoryItemViewHolder>() {
    @JvmField
    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesListAdapter.RepositoryItemViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        return RepositoryItemViewHolder(inflater.inflate(R.layout.item_repository, parent, false), itemClickListener, context!!)
    }

    override fun onBindViewHolder(holder: RepositoriesListAdapter.RepositoryItemViewHolder, position: Int) {
        val repositoryModel = repositoryList[position]
        holder.bindData(repositoryModel)
    }

    override fun getItemCount(): Int = repositoryList.size

    class RepositoryItemViewHolder(itemView: View, private val itemClickListener: OnItemSelectedListener, val context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bindData(repositoryModel: RepositoryModel) {
            val repositoryName = repositoryModel.name
            val repositoryDescription = repositoryModel.description
            val repositoryUpdateTime = repositoryModel.updatedAt
            val hasWiki = repositoryModel.isHasWiki
            itemView.tv_repository_name.text = repositoryName
            itemView.tv_repository_description.text = repositoryDescription
            itemView.tv_repository_updated_time.text = repositoryUpdateTime
            itemView.setBackgroundColor(if (hasWiki) ContextCompat.getColor(context, R.color.red_item_bg) else ContextCompat.getColor(context, R.color.green_item_bg))
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    v?.let { itemClickListener.onClick(it, adapterPosition) }
                }
            })
        }
    }
}
