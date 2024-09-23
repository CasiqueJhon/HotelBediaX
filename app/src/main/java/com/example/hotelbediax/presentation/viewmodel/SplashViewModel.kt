package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelbediax.data.local.DestinationEntity
import com.example.hotelbediax.data.repository.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DestinationRepository
) : ViewModel() {

    private val _destinations = MutableStateFlow<List<DestinationEntity>>(emptyList())
    val destinations: StateFlow<List<DestinationEntity>> get() = _destinations

    suspend fun loadInitialData() {
        viewModelScope.launch {
            repository.loadInitialData()
        }
        _destinations.value = repository.getAllDestinations()
    }
}
