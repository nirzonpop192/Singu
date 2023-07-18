package com.exam.singularity.ui.repository

import com.exam.singularity.remote.apis.ApiServices
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val apiClient: ApiServices,
    ) :
    MainRepository {

    override suspend fun getStores(page: Int) =
        apiClient.getStores(page)
}