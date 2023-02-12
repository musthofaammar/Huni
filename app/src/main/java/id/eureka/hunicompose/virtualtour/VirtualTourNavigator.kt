@file:OptIn(ExperimentalSnapperApi::class)

package id.eureka.hunicompose.virtualtour

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalSnapperApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun VirtualTourNavigator(
    viewModel: VirtualTourViewModel,
    modifier: Modifier = Modifier,
) {

    val currentIndex by viewModel.currentIndex.collectAsState()
    val items by viewModel.rooms.collectAsState()

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(colorResource(id = R.color.white_25))
    ) {

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(colorResource(id = R.color.silver_chalice))
        )

        Text(
            text = "Virtual Tour",
            style = MaterialTheme.typography.h3,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 18.dp, end = 18.dp, top = 8.dp)
        )

        LazyRow(
            state = lazyListState,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            flingBehavior = rememberSnapperFlingBehavior(lazyListState = lazyListState,
                snapOffsetForItem = SnapOffsets.Start),
            modifier = Modifier.padding(PaddingValues(start = 18.dp, end = 18.dp, bottom = 18.dp))
        ) {
            itemsIndexed(items) { index, item ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    border = if (index == currentIndex) BorderStroke(1.dp,
                        colorResource(id = R.color.deep_sapphire)) else null,
                    modifier = Modifier.clickable {
                        scope.launch {
                            viewModel.setIndex(index)
                        }
                    }
                ) {
                    GlideImage(
                        model = item.imageUrl, contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(52.dp)
                            .height(52.dp))

                }
            }
        }
    }
}

@Preview
@Composable
fun VirtualTourNavigatorPreview() {
    HuniComposeTheme {
        VirtualTourNavigator(VirtualTourViewModel())
    }
}