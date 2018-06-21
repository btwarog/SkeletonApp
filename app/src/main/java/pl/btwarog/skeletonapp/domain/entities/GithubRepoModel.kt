package pl.btwarog.skeletonapp.domain.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
data class GithubRepoModel(
        val id: Long,
        val name: String,
        val owner: GithubOwnerModel,
        val private: Boolean,
        val html_url: String,
        val description: String?,
        val fork: Boolean,
        val language: String?)