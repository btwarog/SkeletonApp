package pl.btwarog.skeletonapp.injection.modules

import dagger.Binds
import dagger.Module
import pl.btwarog.skeletonapp.data.remote.repositories.GithubRepository
import pl.btwarog.skeletonapp.data.remote.repositories.GithubRepositoryImpl

/**
 * Module providing domain functionality.
 */
@Module
abstract class DomainModule {

    @Binds
    internal abstract fun bindTestRepository(testRepository: GithubRepositoryImpl): GithubRepository

}
