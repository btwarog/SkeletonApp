package pl.btwarog.skeletonapp.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.btwarog.skeletonapp.injection.scopes.PerActivity
import pl.btwarog.skeletonapp.ui.home.HomeActivity
import pl.btwarog.skeletonapp.ui.home.HomeActivityModule

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [(HomeActivityModule::class)])
    abstract fun bindHomeActivity(): HomeActivity
}