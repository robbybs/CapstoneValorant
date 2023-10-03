package com.rbs.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AgentsListResponse(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("data")
    val data: List<AgentsResponse>
)
