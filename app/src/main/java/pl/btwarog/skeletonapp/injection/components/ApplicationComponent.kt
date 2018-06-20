package pl.btwarog.skeletonapp.injection.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.btwarog.skeletonapp.App
import pl.btwarog.skeletonapp.injection.modules.ActivityBindingModule
import pl.btwarog.skeletonapp.injection.modules.ApiModule
import pl.btwarog.skeletonapp.injection.modules.AppModule
import pl.btwarog.skeletonapp.injection.modules.DomainModule
import javax.inject.Singleton

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityBindingModule::class), (ApiModule::class), (AppModule::class), (DomainModule::class)])
interface ApplicationComponent : AndroidInjector<App>, BaseApplicationComponentInjector {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }
}