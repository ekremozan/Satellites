package ekrem.ozan.satellites.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import ekrem.ozan.satellites.data.local.SatelliteDatabase
import ekrem.ozan.satellites.data.local.dao.SatellitePositionDao
import ekrem.ozan.satellites.data.local.models.SatellitePositionItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SatellitePositionsDaoTest {

    private lateinit var database: SatelliteDatabase
    private lateinit var dao: SatellitePositionDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SatelliteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.satellitePositionDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertSatellitePositionsItem() = runBlocking {
        val item = listOf(SatellitePositionItem(
            satelliteId = 1,
            posX = 0.864328541,
            posY = 0.323434385,
            id = 1
        ))
        dao.insertPositions(item)
        val insertedItem = dao.observeAllPositions(item.first().id).first()

        assertThat(insertedItem).isEqualTo(item)
    }

    @Test
    fun checkIsSatellitePositionItemExist() = runBlocking {
        val item = listOf(SatellitePositionItem(
            satelliteId = 1,
            posX = 0.864328541,
            posY = 0.323434385,
            id = 1)
        )
        dao.insertPositions(item)
        val insertedItem = dao.observeSatellitePositionExist(item.first().id).first()

        assertThat(insertedItem).isGreaterThan(0)
    }
}