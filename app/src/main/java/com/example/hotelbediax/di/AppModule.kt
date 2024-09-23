package com.example.hotelbediax.di

import android.content.Context
import androidx.room.Room
import com.example.hotelbediax.data.local.DestinationDao
import com.example.hotelbediax.data.local.DestinationDatabase
import com.example.hotelbediax.data.remote.DestinationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * The base URL is not ready yet, so we will use a placeholder URL.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDestinationService(retrofit: Retrofit): DestinationService {
        return retrofit.create(DestinationService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DestinationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DestinationDatabase::class.java,
            "destination_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDestinationDao(database: DestinationDatabase): DestinationDao {
        return database.destinationDao()
    }

}
