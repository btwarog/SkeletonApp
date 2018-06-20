package pl.btwarog.skeletonapp.data.remote.repositories

import io.reactivex.Single
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
interface GithubRepository {
    fun userRepos(userName: String): Single<List<GithubRepoModel>>
}