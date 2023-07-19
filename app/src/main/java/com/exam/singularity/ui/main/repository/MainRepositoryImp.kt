package com.exam.singularity.ui.main.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.exam.singularity.remote.apis.ApiServices
import com.exam.singularity.ui.main.pagging_source.StoresPagingSource
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val apiClient: ApiServices,
    ) :
    MainRepository {

    override suspend fun getStores(page: Int) =
        apiClient.getStores(page)

    override fun getStoresPagging() =
        Pager(
            config = PagingConfig(
                pageSize = 6,

                enablePlaceholders = false
            ),
            pagingSourceFactory = { StoresPagingSource(apiClient) }
        ).flow
}