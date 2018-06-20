package pl.btwarog.skeletonapp.ui.home.homeOne

import nucleus5.view.ViewWithPresenter
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel

/**
 *  View for [HomeOnePresenter],
 *  @interface for [HomeOneFragment].
 *  Presents methods for resolving UI visible actions.
 */
interface HomeOneView : ViewWithPresenter<HomeOnePresenter> {
    fun onResultReceived(response: List<GithubRepoModel>?)
}