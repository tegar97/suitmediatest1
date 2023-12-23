package com.tegar.suitmediatest1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tegar.suitmediatest1.data.ResultState
import com.tegar.suitmediatest1.data.remote.response.User
import com.tegar.suitmediatest1.data.remote.response.UserResponse
import com.tegar.suitmediatest1.data.repository.UserRepository

class ThirdScreenViewModel( repository: UserRepository) : ViewModel() {

   val users : LiveData<PagingData<User>> = repository.getListUser().cachedIn(viewModelScope)

}