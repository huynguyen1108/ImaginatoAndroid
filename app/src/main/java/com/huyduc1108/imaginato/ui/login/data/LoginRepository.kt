package com.huyduc1108.imaginato.ui.login.data

import com.huyduc1108.imaginato.data.resultLiveDataApiToken
import com.huyduc1108.imaginato.data.resultLiveDataLocal
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDao: LoginDao,
    private val remoteDataSource: LoginRemoteDataSource
) {
    fun login(username: String, password: String) = resultLiveDataApiToken(
        networkCall = { remoteDataSource.login(username, password) },
        saveCallResult = { userResponse, xAcc ->
            userResponse?.let {
                it.user?.let { data ->
                    xAcc?.let { xAcc ->
                        data.xAcc = xAcc
                        loginDao.insertAll(data)
                    } ?: kotlin.run {
                        loginDao.insertAll(data)
                    }
                }
            }
        }
    )

    fun getUser() = resultLiveDataLocal(
        databaseQuery = { loginDao.getUserAll() }
    )
}