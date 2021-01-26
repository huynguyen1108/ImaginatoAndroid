package com.huyduc1108.imaginato.ui.main.view

import com.huyduc1108.imaginato.R
import com.huyduc1108.imaginato.base.BaseActivity
import com.huyduc1108.imaginato.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutId(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel>? = MainViewModel::class.java

    override fun init() {

    }
}