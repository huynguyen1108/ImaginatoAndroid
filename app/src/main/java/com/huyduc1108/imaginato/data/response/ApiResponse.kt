package com.huyduc1108.imaginato.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("errorCode")
    var errorCode: Int? = null,
    @SerializedName("response")
    var response: T? = null,
    @SerializedName("errorMessage")
    var errorMessage: String? = null
)