package com.huyduc1108.imaginato.data

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val xAcc: String? = ""
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?, xAcc: String? = null): Result<T> {
            return Result(Status.SUCCESS, data, null, xAcc = xAcc)
        }

        fun <T> error(message: String? = null, data: T? = null): Result<T> {
            return Result(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null)
        }
    }
}