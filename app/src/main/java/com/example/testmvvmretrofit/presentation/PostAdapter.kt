package com.example.testmvvmretrofit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testmvvmretrofit.R
import com.example.testmvvmretrofit.domain.models.NetworkPostDomainModel

class PostAdapter : ListAdapter<NetworkPostDomainModel, PostAdapter.Holder>(Comparator()) {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById<TextView>(R.id.postId)
        val title = view.findViewById<TextView>(R.id.postTitle)
        val body = view.findViewById<TextView>(R.id.postBody)

        fun bind(model: NetworkPostDomainModel) {
            id.text = model.id.toString()
            title.text = model.title
            body.text = model.body
        }
    }

    class Comparator : DiffUtil.ItemCallback<NetworkPostDomainModel>() {
        override fun areItemsTheSame(
            oldItem: NetworkPostDomainModel,
            newItem: NetworkPostDomainModel,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NetworkPostDomainModel,
            newItem: NetworkPostDomainModel,
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }
}
