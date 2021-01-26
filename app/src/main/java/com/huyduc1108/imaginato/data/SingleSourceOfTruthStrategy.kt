package com.huyduc1108.imaginato.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.huyduc1108.imaginato.ui.login.data.reponse.User
import com.huyduc1108.imaginato.ui.login.data.reponse.UserResponse
import kotlinx.coroutines.Dispatchers


fun <A> resultLiveDataApi(
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
) =
    liveData(Dispatchers.IO) {
        emit(Result.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            emit(Result.success(responseStatus.data))
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
//            emitSource(source)
        }
    }

fun resultLiveDataApiToken(
    networkCall: suspend () -> Result<UserResponse?>,
    saveCallResult: suspend (UserResponse?, String?) -> Unit
) =
    liveData(Dispatchers.IO) {
        emit(Result.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            emit(Result.success(responseStatus.data))
            saveCallResult(responseStatus.data, responseStatus.xAcc)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
        }
    }

fun <T> resultLiveDataLocal(
    databaseQuery: () -> LiveData<T>
) =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)
    }


fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<A>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = databaseQuery.invoke().map { Result.success(it) }
//        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            emit(Result.success(responseStatus.data))
            responseStatus.data?.let {
                saveCallResult(responseStatus.data)
            }

        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
//            emitSource(source)
        }
    }