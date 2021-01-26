package com.huyduc1108.imaginato.ui.login.data.reponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("errorCode")
    var errorCode: String? = null,
    @SerializedName("errorMessage")
    var errorMessage: String? = null,
    @SerializedName("user")
    var user: User? = null,
) : Parcelable