package com.tegar.suitmediatest1

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tegar.suitmediatest1.data.repository.UserRepository
import com.tegar.suitmediatest1.di.Injection

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdScreenViewModel(Injection.provideApi(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}