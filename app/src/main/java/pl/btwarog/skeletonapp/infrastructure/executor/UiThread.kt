package pl.btwarog.skeletonapp.infrastructure.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.btwarog.skeletonapp.domain.executor.PostExecutionThread
import javax.inject.Inject

/**
 * Scheduler for testing
 */
class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}