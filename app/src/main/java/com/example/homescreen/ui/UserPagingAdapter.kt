package com.example.homescreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homescreen.databinding.ItemActivityBinding
import com.example.homescreen.model.User

class UserPagingAdapter() :
    PagingDataAdapter<User, UserPagingAdapter.UserViewHolder>(UserComparator()) {


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemActivityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }


    class UserViewHolder(private val binding: ItemActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            titleTextView.text = user.title
            bodyTextView.text = user.body
        }

    }


    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }


}