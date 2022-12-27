package com.example.githubusersearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersearch.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser = ArrayList<Item>()

    fun setList(items: ArrayList<Item>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun userData(item: Item){
            binding.tvUsername.text = item.login
            binding.tvId.text = item.id.toString()
            Glide.with(itemView)
                .load(item.avatarUrl)
                .into(binding.imgAvatar)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        val view = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.userData(listUser[position])
    }

    override fun getItemCount() = listUser.size
}

