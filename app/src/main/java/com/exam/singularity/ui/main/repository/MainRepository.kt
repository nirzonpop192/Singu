package com.exam.singularity.ui.main.repository

import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface MainRepository {
   suspend fun getStores(page: Int): NetworkResponse<StoreResponse, ErrorResponse>
}