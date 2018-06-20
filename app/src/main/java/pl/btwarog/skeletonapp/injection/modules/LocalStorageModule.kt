package pl.btwarog.skeletonapp.injection.modules

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.btwarog.skeletonapp.data.local.AppDatabase
import javax.inject.Singleton

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
@Module
open class LocalStorageModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java,"skeletonApp.db").build()
    }
}