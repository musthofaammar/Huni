package id.eureka.hunicompose.detailhuni

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.theme.KanitFont
import id.eureka.hunicompose.core.util.GradientButton
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.core.util.customTabIndicatorOffset
import id.eureka.hunicompose.home.HuniRentPeriod

@Composable
fun DetailHuniScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column {
            HeaderDetailHuni()

            HuniGeneralInfo(
                name = "Griya Asri Cempaka Raya",
                address = "Jl. Tukad Balian, Renon, No. 78",
                ownerName = "Ahmad Lee",
                star = 4.7
            )

            HuniDetailInfoTab()
        }

        HuniBottomInfo(
            price = 24000000.0,
            period = HuniRentPeriod.Month,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HuniBottomInfo(
    price: Double,
    period: HuniRentPeriod,
    modifier: Modifier = Modifier,
) {
//    var isShowBottomInfo by remember {
//        mutableStateOf(true)
//    }
//
//    AnimatedVisibility(visible = isShowBottomInfo) {
//
//    }

    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(text = "Price",
                    style = MaterialTheme.typography.h3,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.deep_sapphire))

                Text(text = "IDR ${Utils.numberFormatter(price)}/${period.period}",
                    style = MaterialTheme.typography.h2,
                    color = colorResource(id = R.color.deep_sapphire),
                    fontSize = 18.sp)
            }

            GradientButton(
                title = "Rent",
                titleColor = Color.White,
                titleStyle = MaterialTheme.typography.h4.copy(fontSize = 14.sp),
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.denim),
                        colorResource(id = R.color.deep_sapphire),
                    ),
                ),
                onClick = {},
                modifier = Modifier.width(120.dp)
            )
        }
    }
}

@Composable
fun HuniDetailInfoTab() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Details", "Maps", "Reviews")

    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        edgePadding = 24.dp,
        backgroundColor = Color.White,
        selectedTabIndex = selectedTabIndex,
        divider = { TabRowDefaults.Divider(color = Color.Transparent) },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    tabPositions[selectedTabIndex],
                    tabWidths[selectedTabIndex]
                )
            )
        },
    ) {
        tabs.forEachIndexed { tabIndex, s ->
            Tab(
                selected = tabIndex == selectedTabIndex,
                onClick = { selectedTabIndex = tabIndex },
                text = {
                    Text(
                        text = s,
                        style = MaterialTheme.typography.h3,
                        fontSize = 18.sp,
                        color = if (selectedTabIndex == tabIndex) colorResource(
                            id = R.color.deep_sapphire
                        ) else colorResource(id = R.color.silver_chalice),
                        onTextLayout = { textLayoutResult ->
                            tabWidths[tabIndex] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun HuniGeneralInfo(
    name: String,
    address: String,
    ownerName: String,
    star: Double,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h3,
                fontSize = 18.sp,
                color = colorResource(id = R.color.onyx),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = colorResource(id = R.color.dark_yellow),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 2.dp)
                )

                Text(
                    text = "$star",
                    style = MaterialTheme.typography.h4,
                    color = colorResource(id = R.color.silver_chalice),
                    fontSize = 16.sp
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = colorResource(id = R.color.onyx),
                    modifier = Modifier
                        .size(12.dp)
                )

                Text(
                    text = address,
                    style = MaterialTheme.typography.h4,
                    color = colorResource(id = R.color.silver_chalice),
                    fontSize = 12.sp
                )
            }

            Button(
                shape = RoundedCornerShape(8.dp),
                onClick = {}
            ) {
                Text(
                    text = "Virtual Tour",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.h3
                )
            }
        }

        Divider(
            Modifier
                .padding(vertical = 16.dp)
                .height(0.8.dp)
                .background(color = colorResource(id = R.color.pastel_grey))
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Column(
                ) {
                    Text(
                        text = ownerName,
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.h3,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = "Owner", fontSize = 12.sp, style = MaterialTheme.typography.h4,
                        color = colorResource(
                            id = R.color.silver_chalice,
                        ),
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.deep_sapphire)),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Call,
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier
                                .height(18.dp)
                                .width(16.dp)
                        )
                    }
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.deep_sapphire)),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Chat,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .height(18.dp)
                                .width(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeaderDetailHuni(modifier: Modifier = Modifier) {

    val images = listOf(
        R.drawable.hotel_1,
        R.drawable.hotel_3,
        R.drawable.hotel_4
    )

    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
    ) {

        HorizontalPager(
            count = images.size,
            state = pagerState
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 24.dp, start = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = colorResource(
                    id = R.color.deep_sapphire
                )
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 24.dp, end = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = null,
                tint = colorResource(
                    id = R.color.deep_sapphire
                )
            )
        }

        ImageIndicator(
            itemCount = images.size,
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageIndicator(
    itemCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        for (position in 0 until itemCount) {
            Indicator(isSelected = pagerState.currentPage == position)
        }
    }
}

@Composable
fun Indicator(modifier: Modifier = Modifier, isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 32.dp else 4.dp)

    Box(
        modifier = modifier
            .height(4.dp)
            .width(width.value)
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (isSelected) colorResource(id = R.color.deep_sapphire) else colorResource(
                    id = R.color.white
                )
            )
    )
}

@Preview(showBackground = true)
@Composable
fun DetailHuniScreenPreview() {
    HuniComposeTheme {
        DetailHuniScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun HuniGeneralInfo() {
    HuniComposeTheme {
        HuniGeneralInfo(
            name = "Griya Asri Cempaka Raya",
            address = "Jl. Tukad Balian, Renon, No. 78",
            ownerName = "Ahmad Lee",
            star = 4.7
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HuniBottomInfoPreview() {
    HuniComposeTheme {
        HuniBottomInfo(price = 240000000.0, period = HuniRentPeriod.Month)
    }
}