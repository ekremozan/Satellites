package ekrem.ozan.satellites.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ekrem.ozan.satellites.data.repository.SatellitePositionsRepositoryImpl
import ekrem.ozan.satellites.data.repository.SatelliteRepositoryImpl
import ekrem.ozan.satellites.domain.repository.SatellitePositionsRepository
import ekrem.ozan.satellites.domain.repository.SatelliteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSatelliteRepository(repositoryImpl: SatelliteRepositoryImpl): SatelliteRepository

    @Singleton
    @Binds
    abstract fun bindSatellitePositionsRepository(repositoryImpl: SatellitePositionsRepositoryImpl): SatellitePositionsRepository
}