package com.huyduc1108.imaginato.ui.login.view

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.huyduc1108.imaginato.R
import com.huyduc1108.imaginato.api.ApiConstants
import com.huyduc1108.imaginato.base.BaseActivity
import com.huyduc1108.imaginato.data.Result
import com.huyduc1108.imaginato.databinding.ActivityLoginBinding
import com.huyduc1108.imaginato.ui.login.data.reponse.User
import com.huyduc1108.imaginato.ui.login.data.reponse.UserResponse
import com.huyduc1108.imaginato.ui.main.view.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun layoutId() = R.layout.activity_login

    override fun viewModelClass() = LoginViewModel::class.java

    override fun init() {
        initListener()
//        checkUserLocal()
    }

    private fun loginSuccess(userResponse: UserResponse?) {
        userResponse?.let {
            if (ApiConstants.SUCCESS_API == it.errorCode) {
                switchScreen()
            } else {
                Toast.makeText(baseContext, it.errorMessage, Toast.LENGTH_SHORT).show()
            }
        } ?: kotlin.run {
            Toast.makeText(baseContext, getString(R.string.error_api), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {
        binding?.btnLogin?.setOnClickListener {
            val userName = binding?.edtUsername?.text.toString()
            val password = binding?.edtPassword?.text.toString()
            if (validate(userName, password)) {
                viewModel!!.login(userName, password).observe(this, {
                    when (it.status) {
                        Result.Status.LOADING -> {
                            showDialogLoading()
                        }
                        Result.Status.SUCCESS -> {
                            hiddenDialogLoading()
                            loginSuccess(it.data)
                        }
                        Result.Status.ERROR -> {
                            hiddenDialogLoading()
                        }
                    }
                })
            }

        }
    }

    private fun validate(userName: String, password: String): Boolean {
        if (userName.isEmpty()) {
            binding?.tlUsername?.error = getString(R.string.empty_username)
            return false
        }
        if (userName.length > 30) {
            binding?.tlUsername?.error = getString(R.string.max_length_30)
            return false
        }
        if (password.isEmpty()) {
            binding?.tlUsername?.error = null
            binding?.tlPassword?.error = getString(R.string.empty_password)
            return false
        }
        if (password.length > 16) {
            binding?.tlUsername?.error = null
            binding?.tlPassword?.error = getString(R.string.max_length_16)
            return false
        }
        binding?.tlPassword?.error = null
        return true
    }

    private fun checkUserLocal() {
        viewModel?.getUser()?.observe(this, Observer {
            when (it.status) {
                Result.Status.LOADING -> {
                    showDialogLoading()
                }
                Result.Status.SUCCESS -> {
                    hiddenDialogLoading()
                    it.data?.let { users ->
                        if (users.isNotEmpty()) {
                            switchScreen()
                        }
                    }
                }
                Result.Status.ERROR -> {
                    hiddenDialogLoading()
                }
            }
        })
    }

    private fun switchScreen(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}