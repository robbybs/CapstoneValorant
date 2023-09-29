package com.rbs.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AgentsResponse(
    @field:SerializedName("uuid")
    val id: String,

    @field:SerializedName("displayName")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("fullPortrait")
    val image: String
)
