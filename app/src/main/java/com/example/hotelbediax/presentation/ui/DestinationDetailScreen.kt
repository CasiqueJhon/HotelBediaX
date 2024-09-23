package com.example.hotelbediax.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hotelbediax.R
import com.example.hotelbediax.presentation.viewmodel.DestinationViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DestinationDetailScreen(destinationId: Int, viewModel: DestinationViewModel = hiltViewModel()) {
    // Observa el StateFlow de selectedDestination
    val destination by viewModel.selectedDestination.collectAsState()

    // Llamamos a getDestinationById cuando la pantalla se carga
    LaunchedEffect(destinationId) {
        viewModel.getDestinationById(destinationId)
    }

    destination?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB)) // Cambia los colores según el estilo que desees
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)) // Para bordes redondeados en la columna
                    .background(Color.White) // Fondo blanco para los detalles
                    .padding(16.dp) // Padding interno para los textos
            ) {
                // Sección de la imagen con un encabezado grande
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)) // Bordes redondeados en la imagen
                ) {
                    GlideImage(
                        imageModel = it.imageUrl,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize(),
                        placeHolder = painterResource(R.drawable.baseline_card_travel_24),
                        error = painterResource(R.drawable.baseline_error_24)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nombre del destino
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ubicación
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_location),
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it.location,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Aquí podrías agregar más detalles, como un botón o información adicional.
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


