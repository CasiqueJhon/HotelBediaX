package com.example.hotelbediax.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DestinationEntity::class], version = 1)
abstract class DestinationDatabase : RoomDatabase() {
    abstract fun destinationDao(): DestinationDao
}