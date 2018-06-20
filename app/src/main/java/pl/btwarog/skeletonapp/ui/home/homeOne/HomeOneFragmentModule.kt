package pl.btwarog.skeletonapp.ui.home.homeOne

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import pl.btwarog.skeletonapp.injection.scopes.PerFragment

/**
 *  2018-01-15
 */
@Module
abstract class HomeOneFragmentModule {

    @Binds
    @PerFragment
    abstract fun fragment(homeOneFragment: HomeOneFragment): Fragment
}