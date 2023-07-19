package com.exam.singularity.ui.main.pagging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.exam.singularity.remote.apis.ApiServices
import com.exam.singularity.ui.main.model.StoreResponse


class StoresPagingSource(
    val client: ApiServices,
) : PagingSource<Int, StoreResponse.StoreDataModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreResponse.StoreDataModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = client.getStoresTest( nextPageNumber)
            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber == response.meta.last_page) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StoreResponse.StoreDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}