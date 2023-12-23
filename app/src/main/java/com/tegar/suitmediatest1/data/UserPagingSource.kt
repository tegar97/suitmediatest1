package com.tegar.suitmediatest1.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tegar.suitmediatest1.data.remote.response.User
import com.tegar.suitmediatest1.data.remote.response.UserResponse
import com.tegar.suitmediatest1.data.remote.retrofit.ApiService

class UserPagingSource(private val apiService : ApiService) : PagingSource<Int, User>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }


    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try{
            Log.d("Trigger" , "Paging trigger")

            val position = params.key ?: INITIAL_PAGE_INDEX

            val responseData = apiService.getUserData(position, params.loadSize)
            Log.d("Response" , responseData.toString())
            val filteredData = responseData?.data?.filterNotNull() ?: emptyList()

            Log.d("filteredData" , filteredData.toString())

            LoadResult.Page(
                data = filteredData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (filteredData.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}