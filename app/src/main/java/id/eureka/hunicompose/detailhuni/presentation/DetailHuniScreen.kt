package id.eureka.hunicompose.detailhuni.presentation

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.util.GradientButton
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.core.util.Utils.stringToPeriod
import id.eureka.hunicompose.core.util.customTabIndicatorOffset
import id.eureka.hunicompose.detailhuni.data.model.Facilities
import id.eureka.hunicompose.detailhuni.data.model.Review
import id.eureka.hunicompose.home.presentation.HuniRentPeriod
import id.eureka.hunicompose.home.presentation.model.Huni
import id.eureka.hunicompose.virtualtour.VirtualTourActivity
import kotlinx.coroutines.launch

@Destination
@Composable
fun DetailHuniScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailHuniViewModel = viewModel(),
    navigator: DestinationsNavigator,
    huni: Huni,
) {

    LaunchedEffect(true) {
        viewModel.setHuniDetail(huni)
    }

    val huniData by viewModel.huni.collectAsState()
    val reviewSelectedRate by viewModel.selectedRate.collectAsState()
    val reviewCounts by viewModel.reviewCounts.collectAsState()
    val filteredReview by viewModel.filteredReviews.collectAsState()

    val lazyListState = rememberLazyListState()
    val showBottomInfo by remember {
        derivedStateOf { !lazyListState.isScrollInProgress }
    }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        LazyColumn(
            state = lazyListState
        ) {
            item {
                HeaderDetailHuni(onBack = { navigator.navigateUp() }, huni = huniData)
            }

            item {
                huniData?.let {
                    HuniGeneralInfo(
                        name = it.name,
                        address = it.address,
                        ownerName = it.ownerName,
                        rate = it.rate
                    )
                }
            }

            item {
                huniData?.let {
                    HuniDetailInfoTab(it.facilities,
                        it.description,
                        reviewCounts,
                        filteredReview,
                        reviewSelectedRate,
                        viewModel::setSelectedRate
                    ) {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(lazyListState.layoutInfo.totalItemsCount - 1)
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = showBottomInfo,
            enter = fadeIn() + slideInVertically() { fullHeight ->
                fullHeight / 2
            },
            exit = fadeOut() + slideOutVertically() { fullHeight ->
                fullHeight / 2
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            huniData?.let {
                HuniBottomInfo(
                    price = it.price,
                    period = stringToPeriod(it.rentPeriod)
                )
            }
        }
    }
}

@Composable
fun HuniBottomInfo(
    price: Double,
    period: HuniRentPeriod,
    modifier: Modifier = Modifier,
) {

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
                Text(
                    text = "Price",
                    style = MaterialTheme.typography.h3,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.deep_sapphire)
                )

                Text(
                    text = "IDR ${Utils.numberFormatter(price)}/${period.period}",
                    style = MaterialTheme.typography.h2,
                    color = colorResource(id = R.color.deep_sapphire),
                    fontSize = 18.sp
                )
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HuniDetailInfoTab(
    facilities: List<Facilities>,
    description: String,
    reviewCounts: List<Int>,
    filteredReviews: List<Review>,
    filterReviewSelected: Int,
    onFilterReviewClick: (Int) -> Unit,
    scrollToEnd: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val tabs = remember {
        listOf("Details", "Maps", "Reviews")
    }

    val tabPagerState = rememberPagerState()

    val density = LocalDensity.current
    val tabWidths = remember(tabs) {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        edgePadding = 24.dp,
        backgroundColor = Color.White,
        selectedTabIndex = tabPagerState.currentPage,
        divider = { TabRowDefaults.Divider(color = Color.Transparent) },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    tabPositions[tabPagerState.currentPage],
                    tabWidths[tabPagerState.currentPage]
                )
            )
        },
    ) {
        tabs.forEachIndexed { tabIndex, tabTitle ->
            Tab(
                selected = tabIndex == tabPagerState.currentPage,
                onClick = {
                    scope.launch {
                        tabPagerState.scrollToPage(tabIndex)
                    }
                },
                text = {
                    Text(
                        text = tabTitle,
                        style = MaterialTheme.typography.h3,
                        fontSize = 18.sp,
                        color = if (tabPagerState.currentPage == tabIndex) colorResource(
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

    HorizontalPager(
        count = tabs.size,
        state = tabPagerState,
        contentPadding = PaddingValues(top = 16.dp, bottom = 88.dp)
    ) { page ->
        when (page) {
            0 -> DetailHuniTab(
                facilities = facilities,
                description = description,
                scrollToEnd = scrollToEnd
            )

            1 -> MapTab()
            2 -> ReviewsTab(
                reviewCounts = reviewCounts,
                filterReviewSelected = filterReviewSelected,
                onFilterReviewClick = onFilterReviewClick,
                filteredReviews = filteredReviews
            )
        }
    }
}

@Composable
fun HuniGeneralInfo(
    name: String,
    address: String,
    ownerName: String,
    rate: Double,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

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
                modifier = Modifier.weight(8.5f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1.5f)
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
                    text = "$rate",
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
                .padding(top = 6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(7f)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = colorResource(id = R.color.onyx),
                    modifier = Modifier
                        .size(12.dp)
                        .padding(end = 4.dp)
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
                onClick = {
                    context.startActivity(Intent(context, VirtualTourActivity::class.java))
                },
                modifier = Modifier
                    .weight(3f)
                    .testTag("virtual_tour_button")
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
fun HeaderDetailHuni(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    huni: Huni?,
) {

    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
    ) {

        HorizontalPager(
            count = huni?.images?.size ?: 0,
            state = pagerState
        ) { page ->
            Image(
                painter = painterResource(id = huni?.images?.get(page) ?: 0),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
        }

        IconButton(
            onClick = onBack,
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
            itemCount = huni?.images?.size ?: 0,
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
//        DetailHuniScreen(onBack = {})
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
            rate = 4.7
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