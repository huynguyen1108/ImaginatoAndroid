package com.huyduc1108.imaginato.di.builder

import com.huyduc1108.imaginato.ui.login.view.LoginActivity
import com.huyduc1108.imaginato.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}