package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.hotelbediax.data.local.DestinationEntity
import com.example.hotelbediax.data.repository.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val destinationRepository: DestinationRepository
) : ViewModel() {


    val pagedDestinations = destinationRepository.getPagedDestinations().flow
        .cachedIn(viewModelScope)

    private val _destinations = MutableStateFlow<List<DestinationEntity>>(emptyList())
    val destinations: StateFlow<List<DestinationEntity>> get() = _destinations

    init {
        loadInitialData()  // Cargamos los datos iniciales al iniciar el ViewModel
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            destinationRepository.loadInitialData()
            _destinations.value = destinationRepository.getAllDestinations()
        }
    }

    fun insertDestination(destination: DestinationEntity) {
        viewModelScope.launch {
            destinationRepository.insertDestination(destination)
            _destinations.value = destinationRepository.getAllDestinations()
        }
    }

    fun deleteDestination(destination: DestinationEntity) {
        viewModelScope.launch {
            destinationRepository.deleteDestination(destination)
            _destinations.value = destinationRepository.getAllDestinations()
        }
    }
}
