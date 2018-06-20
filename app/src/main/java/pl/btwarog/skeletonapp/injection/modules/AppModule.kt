package pl.btwarog.skeletonapp.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.btwarog.skeletonapp.App
import pl.btwarog.skeletonapp.domain.executor.MainExecutionThread
import pl.btwarog.skeletonapp.domain.executor.PostExecutionThread
import pl.btwarog.skeletonapp.domain.executor.ThreadExecutor
import pl.btwarog.skeletonapp.infrastructure.executor.ExecutionThread
import pl.btwarog.skeletonapp.infrastructure.executor.TaskExecutor
import pl.btwarog.skeletonapp.infrastructure.executor.UiThread
import javax.inject.Singleton

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(taskExecutor: TaskExecutor): ThreadExecutor {
        return taskExecutor
    }

    @Provides
    @Singleton
    fun provideMainExecutionThread(threadExecutor: ThreadExecutor): MainExecutionThread {
        return ExecutionThread(threadExecutor)
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}