package pl.btwarog.skeletonapp.data.remote.repositories

import io.reactivex.Single
import pl.btwarog.skeletonapp.data.remote.GithubApiService
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel
import javax.inject.Inject

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
class GithubRepositoryImpl @Inject constructor(private val githubApiService: GithubApiService) : GithubRepository {
    override fun userRepos(userName: String): Single<List<GithubRepoModel>> {
        return githubApiService.userRepos(userName)
    }
}