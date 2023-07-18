package com.exam.singularity.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.exam.singularity.ui.main.repository.MainRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _getStoresResult =
        MutableSharedFlow<NetworkResponse<StoreResponse, ErrorResponse>>()
    var getStoresResult: Flow<NetworkResponse<StoreResponse, ErrorResponse>> =
        _getStoresResult

    fun getStores(page: Int) {
        viewModelScope.launch {
            _getStoresResult.emit(mainRepository.getStores(page))
        }

    }
}