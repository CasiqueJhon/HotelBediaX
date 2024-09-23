package com.example.hotelbediax.data.remote

import com.example.hotelbediax.data.local.DestinationEntity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DestinationService {

    @GET("/destinations")
    suspend fun getDestinations(): List<DestinationEntity>

    @POST("/destinations")
    suspend fun createDestination(@Body destination: DestinationEntity)

    @PUT("/destinations/{id}")
    suspend fun updateDestination(@Path("id") id: String, @Body destination: DestinationEntity)

    @DELETE("/destinations/{id}")
    suspend fun deleteDestination(@Path("id") id: String)
}
