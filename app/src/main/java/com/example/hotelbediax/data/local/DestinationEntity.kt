package com.example.hotelbediax.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "destination",
    indices = [Index(value = ["name"], unique = true)]
)
data class DestinationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val location: String,
    val imageUrl: String
)

