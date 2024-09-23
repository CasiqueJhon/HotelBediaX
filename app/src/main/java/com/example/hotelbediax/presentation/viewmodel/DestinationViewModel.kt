package com.example.hotelbediax.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.hotelbediax.data.repository.DestinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val destinationRepository: DestinationRepository
) : ViewModel() {


    val pagedDestinations = destinationRepository.getPagedDestinations().flow
        .cachedIn(viewModelScope)
}
