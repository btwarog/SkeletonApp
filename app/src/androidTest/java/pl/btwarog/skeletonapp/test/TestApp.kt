package pl.btwarog.skeletonapp.test

import android.app.Activity
import android.app.Application
import android.app.Service
import android.support.multidex.MultiDex
import com.beviado.app.mobile.injection.ComponentReflectionInjector
import com.beviado.app.test.injection.components.DaggerTestApplicationComponent
import pl.btwarog.skeletonapp.test.injection.components.TestApplicationComponent
import com.github.ajalt.reprint.core.Reprint
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import pl.btwarog.skeletonapp.injection.ComponentReflectionInjector
import pl.btwarog.skeletonapp.injection.Injector
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector, HasServiceInjector, Injector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    private lateinit var appComponent: TestApplicationComponent

    private lateinit var injector: ComponentReflectionInjector<TestApplicationComponent>

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerTestApplicationComponent
                .builder()
                .application(this)
                .build()

        // injector for simpler injections in presenters
        injector = ComponentReflectionInjector(TestApplicationComponent::class.java, appComponent)

        appComponent.inject(this)
    }

    /**
     * Performs injection
     *
     * @param target
     */
    override fun inject(target: Any) {
        injector.inject(target)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }
}