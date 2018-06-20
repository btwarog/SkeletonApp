package pl.btwarog.skeletonapp

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pl.btwarog.skeletonapp.injection.ComponentReflectionInjector
import pl.btwarog.skeletonapp.injection.Injector
import pl.btwarog.skeletonapp.injection.components.ApplicationComponent
import pl.btwarog.skeletonapp.injection.components.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
class App : Application(), HasActivityInjector, Injector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: ApplicationComponent

    private lateinit var injector: ComponentReflectionInjector<ApplicationComponent>

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        appComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .build()

        injector = ComponentReflectionInjector(ApplicationComponent::class.java, appComponent)

        appComponent.inject(this)
    }



    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    /**
     * Performs injection
     *
     * @param target
     */
    override fun inject(target: Any) {
        injector.inject(target)
    }
}