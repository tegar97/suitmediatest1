package com.tegar.suitmediatest1.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.google.gson.Gson
import com.tegar.suitmediatest1.data.ResultState
import com.tegar.suitmediatest1.data.UserPagingSource
import com.tegar.suitmediatest1.data.remote.response.User
import com.tegar.suitmediatest1.data.remote.response.UserResponse
import com.tegar.suitmediatest1.data.remote.retrofit.ApiConfig
import com.tegar.suitmediatest1.data.remote.retrofit.ApiService
import retrofit2.HttpException

class UserRepository(private val apiService: ApiService){


    fun getListUser() : LiveData<PagingData<User>>  {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }


}