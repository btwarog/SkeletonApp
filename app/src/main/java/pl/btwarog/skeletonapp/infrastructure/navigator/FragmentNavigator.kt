package pl.btwarog.skeletonapp.infrastructure.navigator

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import pl.btwarog.skeletonapp.infrastructure.FragmentsContainer
import pl.btwarog.skeletonapp.ui.home.homeOne.HomeOneFragment
import javax.inject.Inject

/**
 * Navigator for home bottom navigation panel
 */
class FragmentNavigator @Inject constructor(fragmentsContainer: FragmentsContainer) {

    private val activity: FragmentActivity = fragmentsContainer as FragmentActivity

    @IdRes
    private val fragmentContainerViewId: Int = fragmentsContainer.fragmentContainerViewId

    fun goToHomeOne() {
        toFragment(HomeOneFragment())
    }

    private fun toFragment(fragment: Fragment) {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(fragmentContainerViewId, fragment)
                .commit()
    }


}