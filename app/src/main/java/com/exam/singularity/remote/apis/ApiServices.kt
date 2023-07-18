package com.exam.singularity.remote.apis


import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse


import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiServices {


    @GET("api/stores")
    suspend fun getStores(): NetworkResponse<StoreResponse, ErrorResponse>











}

