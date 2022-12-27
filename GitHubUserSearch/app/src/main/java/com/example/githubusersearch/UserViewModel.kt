package com.example.githubusersearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel {
    val listUser = MutableLiveData<ArrayList<UserResponse>>()




}