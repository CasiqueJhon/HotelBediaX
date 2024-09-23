package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hotelbediax.data.repository.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DestinationRepository
) : ViewModel() {

    suspend fun loadInitialData() {
        repository.loadInitialData()
    }
}
