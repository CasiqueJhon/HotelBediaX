package com.example.hotelbediax.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hotelbediax.R
import com.example.hotelbediax.presentation.viewmodel.AddDestinationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewDestinationScreen(
    onDestinationAdded: () -> Unit,
    onBack: () -> Unit,
    viewModel: AddDestinationViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var detailedDescription by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    val destinationAdded by viewModel.destinationAdded.collectAsState()

    val isFormValid = name.isNotBlank() && description.isNotBlank() &&
            detailedDescription.isNotBlank() && location.isNotBlank() && imageUrl.isNotBlank()

    LaunchedEffect(destinationAdded) {
        if (destinationAdded) {
            onDestinationAdded()
            viewModel.resetDestinationAddedFlag()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.add_new_destination),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            // Campos de texto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.destination_name)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF8E44AD),
                    textColor = Color.Black,
                    placeholderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.destination_description)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF8E44AD),
                    textColor = Color.Black,
                    placeholderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = detailedDescription,
                onValueChange = { detailedDescription = it },
                label = { Text(stringResource(R.string.destination_detailed_description)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF8E44AD),
                    textColor = Color.Black,
                    placeholderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text(stringResource(R.string.destination_location)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF8E44AD),
                    textColor = Color.Black,
                    placeholderColor = Color.Gray
                )
            )

            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text(stringResource(R.string.destination_image_url)) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3498DB),
                    unfocusedBorderColor = Color(0xFF8E44AD),
                    textColor = Color.Black,
                    placeholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addNewDestination(name, description, detailedDescription, location, imageUrl)
                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF3498DB) else Color.DarkGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.add_destination))
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF8E44AD)
                ),
                border = BorderStroke(1.dp, Color(0xFF8E44AD))
            ) {
                Text(text = stringResource(R.string.back))
            }
        }
    }
}



