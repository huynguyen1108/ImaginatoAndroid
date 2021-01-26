package com.huyduc1108.imaginato.ui.login.view

import androidx.lifecycle.ViewModel
import com.huyduc1108.imaginato.ui.login.data.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel(){
    fun login (username: String, password: String) = repository.login(username, password)

    fun getUser() = repository.getUser()
}