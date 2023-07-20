package com.exam.singularity.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.exam.singularity.remote.ErrorResponse
import com.exam.singularity.ui.main.model.AttendanceResponse
import com.exam.singularity.ui.main.model.StoreResponse
import com.exam.singularity.ui.main.repository.MainRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
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

    private var _setAttendance =
        MutableSharedFlow<NetworkResponse<AttendanceResponse, ErrorResponse>>()
    var setAttendanceResult: Flow<NetworkResponse<AttendanceResponse, ErrorResponse>> =
        _setAttendance
    fun setAttendance( name: String,
                       userId: Int,
                       latitude: Double,
                       longitude: Double,
                       request_id: String) {
        viewModelScope.launch {
            _setAttendance.emit(mainRepository.setAttendance(name,userId,latitude,longitude,request_id))
        }

    }

    private var _getStoresPaggingResult = MutableSharedFlow<PagingData<StoreResponse.StoreDataModel>>()
    var getStoresPaggingResult: Flow<PagingData<StoreResponse.StoreDataModel>> = _getStoresPaggingResult

    fun getStoresPagging() = viewModelScope.launch {
        mainRepository.getStoresPagging().cachedIn(viewModelScope).collectLatest {
            _getStoresPaggingResult.emit(it)
        }
    }
}