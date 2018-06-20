package pl.btwarog.skeletonapp.test.injection.modules

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import pl.btwarog.skeletonapp.data.remote.repositories.GithubRepository
import javax.inject.Singleton

/**
 * Module providing domain functionality.
 */
@Module
open class TestDomainModule {
    @Provides
    @Singleton
    internal fun provideGithubRepository(): GithubRepository {
        return mock()
    }
}
