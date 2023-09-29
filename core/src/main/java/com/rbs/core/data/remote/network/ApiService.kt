package com.rbs.core.data.remote.network

import com.rbs.core.data.remote.response.AgentsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("agents")
    suspend fun getAgentsList(
        @Query("isPlayableCharacter") validation: Boolean = true
    ): AgentsListResponse
}