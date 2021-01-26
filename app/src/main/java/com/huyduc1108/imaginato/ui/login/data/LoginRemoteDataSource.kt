package com.huyduc1108.imaginato.ui.login.data

import com.huyduc1108.imaginato.api.ApiService
import com.huyduc1108.imaginato.api.BaseDataSource
import com.huyduc1108.imaginato.data.Result
import com.huyduc1108.imaginato.data.response.ApiResponse
import com.huyduc1108.imaginato.ui.login.data.reponse.User
import com.huyduc1108.imaginato.ui.login.data.reponse.UserResponse
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BaseDataSource() {

    suspend fun login(email: String, password: String): Result<UserResponse?> {
        val user = UserRequestBody(password, email)
        return getResult { apiService.login(user) }
    }

}