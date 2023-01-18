package com.nextgen.kmtest.ui.thirdscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nextgen.kmtest.data.remote.response.DataItem
import com.nextgen.kmtest.databinding.ItemUserLayoutBinding

class UserAdapter(): PagingDataAdapter<DataItem, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    var onClick: ((DataItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(data)
    }

    inner class UserViewHolder(private val binding: ItemUserLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.tvEmail.text = data.email
            binding.tvUsername.text = data.firstName
            Glide.with(itemView.context)
                .load(data.avatar)
                .circleCrop()
                .into(binding.profile)
        }
        init {
            binding.root.setOnClickListener {
                onClick?.invoke(getItem(adapterPosition)!!)
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}