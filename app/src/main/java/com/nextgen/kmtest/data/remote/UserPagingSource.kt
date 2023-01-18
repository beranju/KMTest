package com.nextgen.kmtest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nextgen.kmtest.data.remote.response.DataItem

class UserPagingSource(private val apiService: ApiService): PagingSource<Int, DataItem>() {

    companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            val anchorPage =state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position =params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUser(position, params.loadSize).data!!

            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1

            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}