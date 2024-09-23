package com.example.hotelbediax.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DestinationDao {

    @Query("SELECT * FROM destination")
    suspend fun getAllDestinations(): List<DestinationEntity>

    @Query("SELECT * FROM destination ORDER BY id DESC")
    fun getPagedDestinations(): PagingSource<Int, DestinationEntity>

    @Query("SELECT * FROM destination WHERE id = :id LIMIT 1")
    suspend fun getDestinationById(id: Int): DestinationEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDestination(destination: DestinationEntity)

    @Delete
    suspend fun deleteDestination(destination: DestinationEntity)
}
