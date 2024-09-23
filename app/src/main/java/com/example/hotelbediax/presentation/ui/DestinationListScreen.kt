package com.example.hotelbediax.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.request.RequestOptions
import com.example.hotelbediax.R
import com.example.hotelbediax.data.local.DestinationEntity
import com.example.hotelbediax.presentation.viewmodel.DestinationViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DestinationListScreen(viewModel: DestinationViewModel = hiltViewModel(), onAddDestinationClick: () -> Unit) {

    val lazyPagingItems = viewModel.pagedDestinations.collectAsLazyPagingItems()

    val colors = listOf(
        Brush.horizontalGradient(colors = listOf(colorResource(R.color.orange_first_degrade_item), colorResource(R.color.pink_last_degrade_item))),
        Brush.horizontalGradient(colors = listOf(colorResource(R.color.yellow_first_degrade_item), colorResource(R.color.red_last_degrade_item))),
        Brush.horizontalGradient(colors = listOf(colorResource(R.color.blue_first_degrade_item), colorResource(R.color.purple_last_degrade_item))),
        Brush.horizontalGradient(colors = listOf(colorResource(R.color.light_blue_first_degrade_item), colorResource(R.color.cyan_last_degrade_item)))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.list_title),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.small_padding), top = dimensionResource(R.dimen.large_padding))
                    .align(Alignment.CenterStart) // Alineado a la izquierda
            )

            IconButton(
                onClick = onAddDestinationClick,
                modifier = Modifier
                    .size(48.dp) // Tamaño del botón
                    .align(Alignment.TopEnd)
                    .padding(top = dimensionResource(R.dimen.large_padding), end = dimensionResource(R.dimen.medium_padding))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Destination",
                    tint = Color.Black // Color del ícono
                )
            }
        }

        Text(
            text = stringResource(R.string.list_description),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Gray
            ),
            modifier = Modifier
                .padding(bottom = dimensionResource(R.dimen.medium_padding))
                .padding(start = dimensionResource(R.dimen.small_padding))
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(lazyPagingItems.itemCount) { index ->
                val destination = lazyPagingItems[index]

                destination?.let {
                    val backgroundBrush = colors[index % colors.size]
                    DestinationItem(destination = it, backgroundBrush = backgroundBrush)
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                    }
                    loadState.append is LoadState.Error -> {
                        item { Text(stringResource(R.string.error_text), modifier = Modifier.padding(16.dp)) }
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationItem(destination: DestinationEntity, backgroundBrush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundBrush)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = destination.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            GlideImage(
                imageModel = destination.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                requestOptions = {
                    RequestOptions().override(300, 300)
                        .error(R.drawable.baseline_error_24)
                },
                placeHolder = painterResource(R.drawable.baseline_card_travel_24),
                error = painterResource(R.drawable.baseline_error_24)
            )
        }
    }
}











