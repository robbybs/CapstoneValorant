package com.rbs.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agents(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val favorite: Boolean
) : Parcelable