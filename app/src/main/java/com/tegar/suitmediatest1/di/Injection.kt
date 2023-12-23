package com.tegar.suitmediatest1.di

import android.content.Context
import com.tegar.suitmediatest1.data.remote.retrofit.ApiConfig
import com.tegar.suitmediatest1.data.repository.UserRepository

object Injection {

    fun provideApi(context : Context) : UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}