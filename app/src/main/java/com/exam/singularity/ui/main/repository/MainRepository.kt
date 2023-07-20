package com.exam.singularity.ui.main.repository

import androidx.paging.PagingData
import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.AttendanceResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
   suspend fun getStores(page: Int): NetworkResponse<StoreResponse, ErrorResponse>

   fun getStoresPagging(): Flow<PagingData<StoreResponse.StoreDataModel>>
   suspend fun setAttendance(
      name: String,
      userId: Int,
      latitude: Double,
      longitude: Double,
      request_id: String
   ) :NetworkResponse<AttendanceResponse, ErrorResponse>

}