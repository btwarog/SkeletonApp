package pl.btwarog.skeletonapp.infrastructure

import android.support.annotation.IdRes

/**
 *  Provider of Fragment Container in specific View
 */
interface FragmentsContainer {

    /**
     * @return id of specific fragment container on current view
     */
    @get:IdRes
    val fragmentContainerViewId: Int

}
