package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.KeyEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusersearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    val listUser = MutableLiveData<ArrayList<UserResponse>>()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding.apply {
            rvUser.layoutManager = layoutManager
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
            btnSearch.setOnClickListener {
                searchUsers()
            }
            edUsername.setOnKeyListener { v, keyCode, event ->
                if (event.action == keyEvent.ACTION_DOWN && keyCode == keyEvent.KEYCODE_ENTER){
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        getSearchUsers().observe(this, {
            if(it!=null){
                adapter.setList(it)
            }
        })
    }

    private fun setSearchUsers(query: String) {
        showLoading(true)
        val client = ApiConfig.getApiService().getSearchUsers(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody: UserResponse = response.body()
                    if (responseBody != null) {
                        listUser.postValue(responseBody.items)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun getSearchUsers(): LiveData<ArrayList<UserResponse>>{
        return listUser
    }

    private fun searchUsers(){
        binding.apply {
            val query = edUsername.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}