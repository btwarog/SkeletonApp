package pl.btwarog.skeletonapp.data.remote

import io.reactivex.Single
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users/{userName}/repos")
    fun userRepos(@Path("userName") userName: String): Single<List<GithubRepoModel>>

}