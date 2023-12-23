package com.tegar.suitmediatest1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tegar.suitmediatest1.data.remote.response.User
import com.tegar.suitmediatest1.databinding.UserCardBinding

class UsersAdapter(
    private val onItemClickListener: (User) -> Unit
) : PagingDataAdapter<User,UsersAdapter.MyViewHolder>(DIFF_CALLBACK){
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val users = getItem(position)
        if (users != null) {
            holder.bind(users)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.MyViewHolder {
        val binding = UserCardBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return MyViewHolder(binding , onItemClickListener)
    }


    class  MyViewHolder(private val binding: UserCardBinding ,         private val onItemClickListener: (User) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user : User) {
            binding.apply{
                tvItemName.text = user.firstName
                tvItemDescription.text = user.email
                Glide.with(binding.root.context).load(user.avatar).into(binding.imgItemPhoto)
                itemView.setOnClickListener {
                    onItemClickListener.invoke(user)
                }
            }
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}