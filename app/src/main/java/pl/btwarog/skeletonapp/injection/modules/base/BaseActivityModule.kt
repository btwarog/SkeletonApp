package pl.btwarog.skeletonapp.injection.modules.base

import android.support.v4.app.FragmentActivity
import dagger.Module
import dagger.Provides
import pl.btwarog.skeletonapp.infrastructure.FragmentsContainer
import pl.btwarog.skeletonapp.injection.scopes.PerActivity

/**
 *  Basic activity module
 */
@Module
open class BaseActivityModule {
    @Provides
    @PerActivity
    internal fun provideFragmentHostingActivity(activity: FragmentActivity): FragmentsContainer {
        return activity as FragmentsContainer
    }
}