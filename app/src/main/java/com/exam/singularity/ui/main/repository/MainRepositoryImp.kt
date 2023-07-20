package com.exam.singularity.ui.main.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.remote.apis.ApiServices
import com.exam.singularity.ui.main.model.AttendanceResponse
import com.exam.singularity.ui.main.pagging_source.StoresPagingSource
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val apiClient: ApiServices,
    ) :
    MainRepository {

    override suspend fun getStores(page: Int) =
        apiClient.getStores(page)

    override suspend fun setAttendance(
        name: String,
        userId: Int,
        latitude: Double,
        longitude: Double,
        request_id: String
    ): NetworkResponse<AttendanceResponse, ErrorResponse> =
        apiClient.setAttendance(name,userId,latitude,longitude,request_id)

    override fun getStoresPagging() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 36,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { StoresPagingSource(apiClient) }
        ).flow
}