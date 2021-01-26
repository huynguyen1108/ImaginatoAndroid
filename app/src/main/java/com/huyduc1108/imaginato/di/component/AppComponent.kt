package com.huyduc1108.imaginato.di.component

import android.app.Application
import com.huyduc1108.imaginato.App
import com.huyduc1108.imaginato.di.builder.ActivityModule
import com.huyduc1108.imaginato.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}