package pl.btwarog.skeletonapp.test.injection.components

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.btwarog.skeletonapp.injection.components.BaseApplicationComponentInjector
import pl.btwarog.skeletonapp.injection.modules.ActivityBindingModule
import pl.btwarog.skeletonapp.injection.modules.DomainModule
import pl.btwarog.skeletonapp.test.TestApp
import pl.btwarog.skeletonapp.test.injection.modules.TestApiModule
import pl.btwarog.skeletonapp.test.injection.modules.TestApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ActivityBindingModule::class), (TestApplicationModule::class), (TestApiModule::class), (DomainModule::class)])
interface TestApplicationComponent : AndroidInjector<TestApp>, BaseApplicationComponentInjector {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApp): Builder

        fun build(): TestApplicationComponent
    }
}