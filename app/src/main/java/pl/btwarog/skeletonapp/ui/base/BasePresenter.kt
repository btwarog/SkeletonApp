package pl.btwarog.skeletonapp.ui.base

import android.os.Bundle
import icepick.Icepick
import io.reactivex.disposables.CompositeDisposable
import nucleus5.presenter.RxPresenter
import nucleus5.view.ViewWithPresenter
import pl.btwarog.skeletonapp.domain.executor.MainExecutionThread
import pl.btwarog.skeletonapp.domain.executor.PostExecutionThread
import timber.log.Timber
import javax.inject.Inject

/**
 * Base presenter, manages saving state and clears [compositeDisposable] in [onDropView].
 */
open class BasePresenter<V : ViewWithPresenter<*>> : RxPresenter<V>() {

    @Inject
    lateinit var mainExecutionThread: MainExecutionThread

    @Inject
    lateinit var postExecutionThread: PostExecutionThread

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        Timber.d("%s.onCreate", javaClass.simpleName)
        Icepick.restoreInstanceState(this, savedState)
    }

    override fun onSave(state: Bundle) {
        super.onSave(state)
        Icepick.saveInstanceState(this, state)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("%s.onDestroy", javaClass.simpleName)
    }

    override fun onTakeView(v: V?) {
        super.onTakeView(v)
        Timber.d("onTakeView")
    }

    override fun onDropView() {
        super.onDropView()
        compositeDisposable.clear()
        Timber.d("%s.onDropView", javaClass.simpleName)
    }
}
