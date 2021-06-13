package com.picpay.desafio.android.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserViewEntity(
    val image: String,
    val name: String,
    val id: Int,
    val username: String
) : Parcelable