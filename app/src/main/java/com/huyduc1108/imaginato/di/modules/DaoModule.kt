package com.huyduc1108.imaginato.di.modules

import com.huyduc1108.imaginato.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {
    @Singleton
    @Provides
    fun provideLoginDao(db: AppDatabase) = db.loginDao()
}