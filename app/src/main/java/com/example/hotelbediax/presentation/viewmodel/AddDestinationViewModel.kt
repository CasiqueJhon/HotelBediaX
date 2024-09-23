package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AddDestinationViewModel @Inject constructor(
    private val repository: DestinationRepository
) : ViewModel() {

    private val _destinationAdded = MutableStateFlow(false)
    val destinationAdded: StateFlow<Boolean> get() = _destinationAdded

    fun addNewDestination(
        name: String,
        description: String,
        detailedDescription: String,
        location: String,
        imageUrl: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val newDestination = DestinationEntity(
                name = name,
                description = description,
                detailedDescription = detailedDescription,
                location = location,
                imageUrl = imageUrl
            )
            repository.insertDestination(newDestination)
            _destinationAdded.value = true
        }
    }

    fun resetDestinationAddedFlag() {
        _destinationAdded.value = false
    }
}

