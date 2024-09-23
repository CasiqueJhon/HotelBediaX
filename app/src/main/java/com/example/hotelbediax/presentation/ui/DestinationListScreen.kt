package com.example.hotelbediax.presentation.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun DestinationListScreen(viewModel: DestinationViewModel = hiltViewModel()) {
    val lazyPagingItems = viewModel.pagedDestinations.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val destination = lazyPagingItems[index]

            destination?.let {
                DestinationItem(destination = it)
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

@Composable
fun DestinationItem(destination: DestinationEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.small_padding))
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.first_degrade_item),
                        colorResource(id = R.color.last_degrade_item)
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.large_padding))
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
                },
                placeHolder = painterResource(R.drawable.baseline_card_travel_24),
                error = painterResource(R.drawable.baseline_error_24)
            )
        }
    }
}









