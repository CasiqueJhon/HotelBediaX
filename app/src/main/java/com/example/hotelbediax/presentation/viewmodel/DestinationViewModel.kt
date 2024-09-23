package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.hotelbediax.data.local.DestinationEntity
import com.example.hotelbediax.data.repository.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val destinationRepository: DestinationRepository
) : ViewModel() {


    val pagedDestinations = destinationRepository.getPagedDestinations().flow
        .cachedIn(viewModelScope)

    private val _destinations = MutableStateFlow<List<DestinationEntity>>(emptyList())
    val destinations: StateFlow<List<DestinationEntity>> get() = _destinations

    private val _selectedDestination = MutableStateFlow<DestinationEntity?>(null)
    val selectedDestination: StateFlow<DestinationEntity?> get() = _selectedDestination

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            destinationRepository.loadInitialData()
            _destinations.value = destinationRepository.getAllDestinations()
        }
    }

    fun getDestinationById(id: Int) {
        viewModelScope.launch {
            val destination = withContext(Dispatchers.IO) {
                destinationRepository.getDestinationById(id)
            }
            _selectedDestination.value = destination
        }
    }

    fun deleteDestination(destination: DestinationEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            destinationRepository.deleteDestination(destination)
            _destinations.value = destinationRepository.getAllDestinations()
        }
    }

}
