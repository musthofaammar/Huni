package id.eureka.hunicompose.virtualtour

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.util.Utils

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VirtualTourNavigator(
    modifier: Modifier = Modifier,
) {

    val currentIndex by remember {
        mutableStateOf(0)
    }

    val pagerState = rememberPagerState()

    val items = Utils.dummyVirtualTourImages().chunked(5)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(colorResource(id = R.color.white_25))
            .padding(18.dp)
    ) {

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(colorResource(id = R.color.silver_chalice))
                .padding(bottom = 8.dp)
        )

        Text(
            text = "Virtual Tour",
            style = MaterialTheme.typography.h3,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        val horizontalPadding = 16.dp
        val itemWidth = 340.dp
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val contentPadding = PaddingValues(
            start = horizontalPadding,
            end = (screenWidth - itemWidth + horizontalPadding)
        )

        HorizontalPager(
            count = items.size,
            itemSpacing = 8.dp,
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (item in items[page]) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        border = if (page == currentIndex) BorderStroke(
                            1.dp,
                            colorResource(id = R.color.deep_sapphire)
                        ) else null
                    ) {
                        Image(
                            painter = painterResource(id = item),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(52.dp)
                                .height(52.dp)
                        )
                    }
                }
            }
        }

//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 18.dp),
//            horizontalArrangement = Arrangement.spacedBy(6.dp)
//        ) {
//            itemsIndexed(Utils.dummyVirtualTourImages()) { index, item ->
//                Card(
//                    shape = RoundedCornerShape(14.dp),
//                    border = if (index == currentIndex) BorderStroke(1.dp,
//                        colorResource(id = R.color.deep_sapphire)) else null
//                ) {
//                    Image(
//                        painter = painterResource(id = item),
//                        contentDescription = null,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .width(52.dp)
//                            .height(52.dp)
//                    )
//                }
//            }
//        }
    }
}

@Preview
@Composable
fun VirtualTourNavigatorPreview() {
    HuniComposeTheme {
        VirtualTourNavigator()
    }
}