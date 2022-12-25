package com.example.githubuserapps

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapp.ListUserAdapter
import com.example.githubuserapp.User

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()

    }

    private val listUsers: ArrayList<User>
        get() {
            val dataUsername:Array<String> = resources.getStringArray(R.array.data_username)
            val dataName:Array<String> = resources.getStringArray(R.array.data_name)
            val dataLocation:Array<String> = resources.getStringArray(R.array.data_location)
            val dataRepository:Array<String> = resources.getStringArray(R.array.data_repository)
            val dataCompany:Array<String> = resources.getStringArray(R.array.data_company)
            val dataItemFollowers:Array<String> = resources.getStringArray(R.array.data_followers)
            val dataItemFollowing:Array<String> = resources.getStringArray(R.array.data_following)
            val dataAvatar:TypedArray = resources.obtainTypedArray(R.array.data_avatar)

            val listUser = ArrayList<User>()
            for (i in dataUsername.indices) {
                val user = User(
                    dataUsername[i],
                    dataName[i],
                    dataLocation[i],
                    dataRepository[i],
                    dataCompany[i],
                    dataItemFollowers[i],
                    dataItemFollowing[i],
                    dataAvatar.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val moveIntent = Intent(this@MainActivity,DetailGithubUser::class.java)
                moveIntent.putExtra(DetailGithubUser.EXTRA_USER,data)
            }
        })
    }
}