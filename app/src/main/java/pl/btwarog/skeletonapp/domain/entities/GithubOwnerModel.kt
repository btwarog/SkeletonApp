package pl.btwarog.skeletonapp.domain.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
data class GithubOwnerModel(
        @PrimaryKey(autoGenerate = false)
        val id: Long,
        val login: String,
        val avatar_url: String,
        val url: String,
        val site_admin: Boolean
)