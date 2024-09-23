package com.example.hotelbediax.presentation.viewmodel

import com.example.hotelbediax.data.repository.DestinationRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DestinationViewModelTest {

    private lateinit var repository: DestinationRepository

    private lateinit var viewModel: DestinationViewModel

    @Before
    fun setUp() {
        repository = Mockito.mock(DestinationRepository::class.java)
        MockitoAnnotations.openMocks(this)
        viewModel = DestinationViewModel(repository)
    }

    @Test
    fun whenViewModelInitialized_pagedDestinationsInvoked() = runBlockingTest {
        viewModel.pagedDestinations

        verify(repository).getPagedDestinations()
    }
}

