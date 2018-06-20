package pl.btwarog.skeletonapp.ui.home

import android.support.v4.app.FragmentActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.btwarog.skeletonapp.injection.modules.base.BaseActivityModule
import pl.btwarog.skeletonapp.injection.scopes.PerActivity
import pl.btwarog.skeletonapp.injection.scopes.PerFragment
import pl.btwarog.skeletonapp.ui.home.homeOne.HomeOneFragment
import pl.btwarog.skeletonapp.ui.home.homeOne.HomeOneFragmentModule

/**
 *  2018-01-15
 */
@Module(includes = [(BaseActivityModule::class)])
abstract class HomeActivityModule {

    /**
     * Provides the injector for the [HomeOneFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = [(HomeOneFragmentModule::class)])
    abstract fun homeOneFragment(): HomeOneFragment

    @Binds
    @PerActivity
    abstract fun activity(homeActivity: HomeActivity): FragmentActivity
}