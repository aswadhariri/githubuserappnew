package com.example.githubuserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.DetailGithubUser
import com.example.githubuserapps.R

class ListUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        var tvName: TextView = itemView.findViewById(R.id.tv_detail_name)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_detail_location)
        var tvRepository: TextView = itemView.findViewById(R.id.tv_detail_repository)
        var tvCompany: TextView = itemView.findViewById(R.id.tv_detail_company)
        var tvItemFollower: TextView = itemView.findViewById(R.id.tv_item_followers)
        var tvItemFollowing: TextView = itemView.findViewById(R.id.tv_item_following)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_item_avatar)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, name, location, repository, company, item_follower, item_following, avatar) = listUser[position]
        holder.tvUsername.text = username
        holder.tvName.text = name
        holder.tvLocation.text = location
        holder.tvRepository.text = repository
        holder.tvCompany.text = company
        holder.tvItemFollower.text = item_follower
        holder.tvItemFollowing.text = item_following
        avatar?.let { holder.imgAvatar.setImageResource(it) }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
                       }
        }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    override fun getItemCount(): Int = listUser.size
}