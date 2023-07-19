package com.exam.singularity.ui.main.repository

import androidx.paging.PagingData
import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
   suspend fun getStores(page: Int): NetworkResponse<StoreResponse, ErrorResponse>

   fun getStoresPagging(): Flow<PagingData<StoreResponse.StoreDataModel>>

}