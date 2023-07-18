package com.exam.singularity.ui.repository

import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface MainRepository {
   suspend fun getStores(page: Int): NetworkResponse<StoreResponse, ErrorResponse>
}