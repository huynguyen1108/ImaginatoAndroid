package com.huyduc1108.imaginato.di.modules

import com.huyduc1108.imaginato.api.ApiService
import com.huyduc1108.imaginato.ui.login.data.LoginRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideLoginRemoteDataSource(apiService: ApiService) = LoginRemoteDataSource(apiService)
}