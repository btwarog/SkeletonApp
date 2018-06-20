package pl.btwarog.skeletonapp.ui.home.homeOne

import android.os.Bundle
import pl.btwarog.skeletonapp.data.remote.repositories.GithubRepository
import pl.btwarog.skeletonapp.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

/**
 * Presenter for [HomeOneView]
 */
class HomeOnePresenter : BasePresenter<HomeOneView>(){


    @Inject
    lateinit var  testRepository: GithubRepository

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        restartableLatestCache(REPOS_RESTARTABLE_ID,
                {
                    testRepository
                            .userRepos("btwarog")
                            .toObservable()
                            .observeOn(postExecutionThread.scheduler)
                            .subscribeOn(mainExecutionThread.scheduler())
                },
                {
                    view,response ->
                    stop(REPOS_RESTARTABLE_ID)
                    view.onResultReceived(response)
                },
                {
                    _, throwable ->
                    stop(REPOS_RESTARTABLE_ID)
                    Timber.e(throwable)
                })
    }

    fun fetchData() {
        start(REPOS_RESTARTABLE_ID)
    }

    /**
     * Companion object that holds ids for restartables
     */
    companion object {
        val REPOS_RESTARTABLE_ID = 1
    }
}