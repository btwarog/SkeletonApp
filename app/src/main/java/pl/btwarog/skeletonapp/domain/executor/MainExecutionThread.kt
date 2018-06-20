package pl.btwarog.skeletonapp.domain.executor

import io.reactivex.Scheduler


/**
 * Abstraction created to change change the execution context.
 */
interface MainExecutionThread {
    fun scheduler(): Scheduler
}