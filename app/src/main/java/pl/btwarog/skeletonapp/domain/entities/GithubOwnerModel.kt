package pl.btwarog.skeletonapp.domain.entities

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
data class GithubOwnerModel(
        val id: Long,
        val login: String,
        val avatar_url: String,
        val url: String,
        val site_admin: Boolean
)