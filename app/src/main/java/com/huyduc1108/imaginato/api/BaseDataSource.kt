package com.huyduc1108.imaginato.api

import com.huyduc1108.imaginato.data.Result
import com.huyduc1108.imaginato.ui.login.data.reponse.UserResponse
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if(body is UserResponse){
                    Result.success(body, response.headers()["X-Acc"])
                } else {
                    Result.success(body)
                }
            }
            return error(" ${response.code()} ${response.message()}")

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {

        return Result.error("Network call has failed for a following reason: $message")
    }
}