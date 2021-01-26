package com.huyduc1108.imaginato.api

import com.huyduc1108.imaginato.ui.login.data.UserRequestBody
import com.huyduc1108.imaginato.ui.login.data.reponse.User
import com.huyduc1108.imaginato.ui.login.data.reponse.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(ApiConstants.API_LOGIN)
    suspend fun login(
        @Body userRequest: UserRequestBody
    ): Response<UserResponse?>
}