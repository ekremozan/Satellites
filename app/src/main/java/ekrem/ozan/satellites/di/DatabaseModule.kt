package ekrem.ozan.satellites.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ekrem.ozan.satellites.data.local.SatelliteDatabase
import ekrem.ozan.satellites.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): SatelliteDatabase {
        return Room.databaseBuilder(
            appContext,
            SatelliteDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }
}