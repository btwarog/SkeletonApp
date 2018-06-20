package pl.btwarog.skeletonapp.infrastructure.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import pl.btwarog.skeletonapp.domain.executor.MainExecutionThread
import pl.btwarog.skeletonapp.domain.executor.ThreadExecutor
import javax.inject.Inject

/**
 * Scheduler for testing
 */
class ExecutionThread @Inject constructor(private val threadExecutor: ThreadExecutor) : MainExecutionThread {

    override fun scheduler(): Scheduler {
        return Schedulers.from(threadExecutor)
    }
}