package pl.btwarog.skeletonapp.test.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.btwarog.skeletonapp.domain.executor.MainExecutionThread
import pl.btwarog.skeletonapp.domain.executor.PostExecutionThread
import pl.btwarog.skeletonapp.domain.executor.ThreadExecutor
import pl.btwarog.skeletonapp.infrastructure.executor.TaskExecutor
import pl.btwarog.skeletonapp.infrastructure.executor.UiThread
import pl.btwarog.skeletonapp.test.TestApp
import pl.btwarog.skeletonapp.test.infrastructure.TestExecutionThread
import javax.inject.Singleton

/**
 * Module providing general application functionality.
 */
@Module
open class TestApplicationModule {

    @Provides
    @Singleton
    fun provideContext(testApp: TestApp): Context {
        return testApp
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(taskExecutor: TaskExecutor): ThreadExecutor {
        return taskExecutor
    }

    @Provides
    @Singleton
    fun provideMainExecutionThread(threadExecutor: ThreadExecutor): MainExecutionThread {
        return TestExecutionThread(threadExecutor)
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}
