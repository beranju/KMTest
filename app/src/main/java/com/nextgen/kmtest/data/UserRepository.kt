package com.nextgen.kmtest.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nextgen.kmtest.data.remote.ApiService
import com.nextgen.kmtest.data.remote.UserPagingSource
import com.nextgen.kmtest.data.remote.response.DataItem
import com.nextgen.kmtest.data.remote.response.UserResponse
import com.nextgen.kmtest.helper.SesiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(
    private val apiService: ApiService,
    val sesiManager: SesiManager
) {

    fun getUser(): Flow<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).flow
    }

}