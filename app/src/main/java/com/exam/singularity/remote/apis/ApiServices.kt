package com.exam.singularity.remote.apis


import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.AttendanceResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.haroldadmin.cnradapter.NetworkResponse


import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiServices {


    @GET("api/stores")
    suspend fun getStores( @Query("page") page: Int = 1): NetworkResponse<StoreResponse, ErrorResponse>
    @Headers("Content-Type: application/json")
    @GET("api/stores")
    suspend fun getStoresTest( @Query("page") page: Int = 1): StoreResponse


    @FormUrlEncoded
    @POST("api/attendance")
    suspend fun setAttendance(
        @Field("name") name: String,
        @Field("uid") userId: Int,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double,
        @Field("request_id") request_id: String
    ): NetworkResponse<AttendanceResponse, ErrorResponse>




}

