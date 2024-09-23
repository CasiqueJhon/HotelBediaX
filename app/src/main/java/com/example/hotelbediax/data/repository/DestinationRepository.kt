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

    suspend fun getAllDestinations(): List<DestinationEntity> {
        return destinationDao.getAllDestinations()
    }

    suspend fun insertDestination(destination: DestinationEntity) {
        destinationDao.insertDestination(destination)
    }

    suspend fun deleteDestination(destination: DestinationEntity) {
        destinationDao.deleteDestination(destination)
    }

    suspend fun getDestinationById(id: Int): DestinationEntity? {
        return destinationDao.getDestinationById(id)
    }

    /**
     * if there is no data in the local database, you can simulate loading remote data.
     * by inserting a sample destination into the local database.
     * @return a list of destinations mocked.
     */
    suspend fun loadInitialData() {
        val existingDestinations = destinationDao.getAllDestinations()

        val preloadedDestinations = listOf(
            DestinationEntity(name = "Paris", description = "Capital of love", location = "France", imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a8/Tour_Eiffel_Wikimedia_Commons.jpg"),
            DestinationEntity(name = "New York", description = "The big Apple", location = "USA", imageUrl = "https://i.pinimg.com/564x/92/8a/35/928a35f9b5cd9a061345a963eac4c291.jpg"),
            DestinationEntity(name = "Rome", description = "The eternal city", location = "Italy", imageUrl = "https://i.pinimg.com/564x/4a/5a/43/4a5a43b65778d1747039f49b8e3922e7.jpg"),
            DestinationEntity(name = "Dubai", description = "The skyscrapers city", location = "UAE", imageUrl = "https://i.pinimg.com/564x/36/aa/c2/36aac2e33899187cdbbd6a47616e93a7.jpg"),
            DestinationEntity(name = "Madrid", description = "A lively city", location = "Spain", imageUrl = "https://i.pinimg.com/564x/1a/f8/25/1af825533895f041c2f00c97b12c7a84.jpg"),
            DestinationEntity(name = "Margarita", description = "The beach paradise", location = "Venezuela", imageUrl = "https://i.pinimg.com/564x/e0/19/7f/e0197f354269eeda7d2e4506dfa3eefa.jpg")
        )

        preloadedDestinations.forEach { preloaded ->
            val exists = existingDestinations.any { it.name == preloaded.name && it.location == preloaded.location }
            if (!exists) {
                destinationDao.insertDestination(preloaded)
            }
        }
    }

}

