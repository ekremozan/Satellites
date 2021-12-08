package ekrem.ozan.satellites.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import ekrem.ozan.satellites.data.local.SatelliteDatabase
import ekrem.ozan.satellites.data.local.dao.SatelliteDao
import ekrem.ozan.satellites.data.local.models.SatelliteDetailItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first

@RunWith(AndroidJUnit4::class)
@SmallTest
class SatelliteDaoTest {

    private lateinit var database: SatelliteDatabase
    private lateinit var dao: SatelliteDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SatelliteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.satelliteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertSatelliteItem() = runBlocking {
        val item = SatelliteDetailItem(
            id = 1,
            name = "Starship-1",
            costPerLaunch = 7200000,
            firstFlight = "2021-12-01",
            height = 118,
            mass = 1167000
        )
        dao.insertSatellite(item)
        val insertedItem = dao.observeSatellite(item.id).first().first()

        assertThat(insertedItem).isEqualTo(item)
    }

    @Test
    fun checkIsSatelliteItemExist() = runBlocking {
        val item = SatelliteDetailItem(
            id = 1,
            name = "Starship-1",
            costPerLaunch = 7200000,
            firstFlight = "2021-12-01",
            height = 118,
            mass = 1167000
        )
        dao.insertSatellite(item)
        val insertedItem = dao.observeSatelliteExist(item.id).first()

        assertThat(insertedItem).isEqualTo(1)
    }
}