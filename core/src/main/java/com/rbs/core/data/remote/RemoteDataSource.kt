package com.rbs.core.data.remote

import android.util.Log
import com.rbs.core.data.remote.network.ApiResponse
import com.rbs.core.data.remote.network.ApiService
import com.rbs.core.data.remote.response.AgentsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {

    suspend fun getAgentsList():Flow<ApiResponse<List<AgentsResponse>>> = flow {
        try {
            val response = apiService.getAgentsList()
            val data = response.data

            if (data.isNotEmpty()) emit(ApiResponse.Success(response.data)) else emit(ApiResponse.Empty)

        } catch (exception: Exception) {
            val detailError = exception.toString()
            emit(ApiResponse.Error(detailError))
            Log.e("Error fetch data: ", detailError)
        }
    }.flowOn(Dispatchers.IO)
}