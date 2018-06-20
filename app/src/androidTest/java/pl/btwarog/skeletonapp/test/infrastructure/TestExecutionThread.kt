package pl.btwarog.skeletonapp.test.infrastructure

import android.support.test.espresso.Espresso
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import pl.btwarog.skeletonapp.domain.executor.MainExecutionThread
import pl.btwarog.skeletonapp.domain.executor.ThreadExecutor


/**
 * Scheduler for testing
 */
class TestExecutionThread(private val threadExecutor: ThreadExecutor) : MainExecutionThread {

    var id: Long = 0

    override fun scheduler(): Scheduler {
        val wrapped = Rx2Idler.wrap(Schedulers.from(threadExecutor), "My Scheduler $id")
        Espresso.registerIdlingResources(wrapped)
        id++
        return wrapped
    }
}