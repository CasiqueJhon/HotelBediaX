package com.example.hotelbediax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.hotelbediax.data.local.DestinationDao
import com.example.hotelbediax.data.local.DestinationEntity
import javax.inject.Inject

class DestinationRepository @Inject constructor(
    private val destinationDao: DestinationDao,
) {

    fun getPagedDestinations(): Pager<Int, DestinationEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 8,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { destinationDao.getPagedDestinations() }
        )
    }

    suspend fun insertDestination(destination: DestinationEntity) {
        destinationDao.insertDestination(destination)
    }

    suspend fun deleteDestination(destination: DestinationEntity) {
        destinationDao.deleteDestination(destination)
    }

    /**
     * if there is no data in the local database, you can simulate loading remote data.
     * by inserting a sample destination into the local database.
     * @return a list of destinations mocked.
     */
    suspend fun loadInitialData() {
        val hasData = destinationDao.getAllDestinations().isNotEmpty()
        if (!hasData) {

            destinationDao.insertDestination(
                DestinationEntity(name = "Paris", description = "Capital of love", location = "France", imageUrl = "https://i.pinimg.com/736x/9a/0f/80/9a0f80361e0495d35649fbbc7cc7ccf9.jpg")
            )
            destinationDao.insertDestination(
                DestinationEntity(name = "Tokyo", description = "A vibrant city", location = "Japan", imageUrl = "https://i.pinimg.com/564x/dc/12/01/dc12011c464a71a27804617a107135e0.jpg")
            )
        }
    }

}

