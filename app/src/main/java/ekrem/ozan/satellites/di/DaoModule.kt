package ekrem.ozan.satellites.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ekrem.ozan.satellites.data.local.SatelliteDatabase
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.local.dao.SatellitePositionDao

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    fun provideSatelliteDao(appDatabase: SatelliteDatabase): SatelliteDao {
        return appDatabase.satelliteDao()
    }

    @Provides
    fun provideSatellitePositionDao(appDatabase: SatelliteDatabase): SatellitePositionDao {
        return appDatabase.satellitePositionDao()
    }
}