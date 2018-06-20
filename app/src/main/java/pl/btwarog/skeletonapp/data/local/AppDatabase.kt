package pl.btwarog.skeletonapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Database(entities = [(GithubRepoModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
}