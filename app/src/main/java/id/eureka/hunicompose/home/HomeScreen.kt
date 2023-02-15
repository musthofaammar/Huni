@file:OptIn(ExperimentalSnapperApi::class)

package id.eureka.hunicompose.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.theme.KanitFont
import id.eureka.hunicompose.core.util.HuniCategory
import id.eureka.hunicompose.core.util.SearchBar
import id.eureka.hunicompose.core.util.SectionWithTitleAndSeeAll
import id.eureka.hunicompose.core.util.Utils

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {

    LazyColumn(modifier = modifier) {

        item {
            HomeHeader(userName = "Naufintya", location = "Bali, Indonesia")
        }

        item {

            var query by rememberSaveable { mutableStateOf("") }

            SearchBar(
                hint = "Search Places",
                query = query,
                onTextChanged = {
                    query = it
                },
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .testTag("search_bar")
            )
        }

        item {
            HuniNearbyLocations(onItemClick = onItemClick)
        }

        item {
            HuniPopular(modifier = Modifier.padding(top = 32.dp), onItemClick = onItemClick)
        }
    }
}

@Composable
fun HomeHeader(
    userName: String,
    location: String,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(colorResource(id = R.color.deep_sapphire))
                .padding(bottom = 64.dp, start = 24.dp, end = 24.dp, top = 24.dp)
                .align(Alignment.TopStart)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                val annotatedText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = KanitFont,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append("Welcome, ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = KanitFont,
                            fontWeight = FontWeight.Normal,
                        )
                    ) {
                        append(userName)
                    }
                }

                Text(text = annotatedText, fontSize = 16.sp, color = Color.White)

                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            Text(
                text = "Your current location",
                fontFamily = KanitFont,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.White
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.deep_carmine_pink,
                    ),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )

                Text(
                    text = location,
                    fontFamily = KanitFont,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = colorResource(id = R.color.deep_carmine_pink),
                )
            }
        }

        HuniCategories(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 40.dp)
        )
    }
}

@Composable
fun HuniCategories(
    modifier: Modifier = Modifier,
) {

    val categories = listOf(
        HuniCategoryData("Apartment", Icons.Filled.Apartment),
        HuniCategoryData("House", Icons.Filled.Home),
        HuniCategoryData("Hotel", iconFromDrawable = painterResource(id = R.drawable.hotel)),
        HuniCategoryData("Kost", Icons.Filled.House),
        HuniCategoryData("Homestay", Icons.Filled.HomeWork),
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        items(categories) { item ->
            HuniCategory(
                title = item.title,
                titleColor = colorResource(id = R.color.deep_sapphire),
                icon = item.icon,
                iconFromDrawable = item.iconFromDrawable,
                iconColor = colorResource(id = R.color.deep_sapphire)
            )
        }
    }
}

@Composable
fun HuniNearbyLocations(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {

    val lazyListState = rememberLazyListState()

    SectionWithTitleAndSeeAll(
        title = "Nearby Your Location",
        modifier = modifier
    ) {
        LazyRow(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            flingBehavior = rememberSnapperFlingBehavior(
                lazyListState = lazyListState,
                snapOffsetForItem = SnapOffsets.Start
            ),
            modifier = Modifier.testTag("nearby_list")
        ) {
            items(Utils.dummyHuniItem()) { item ->
                HuniItemShort(
                    name = item.name,
                    address = item.address,
                    star = item.rate,
                    price = item.price,
                    period = item.rentPeriod,
                    image = painterResource(id = item.image),
                    modifier = Modifier.clickable(onClick = onItemClick)
                )
            }
        }
    }
}

@Composable
fun HuniPopular(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    SectionWithTitleAndSeeAll(
        title = "Popular",
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .animateContentSize()
        ) {
            Utils.dummyHuniItem().forEach { item ->
                HuniItemLong(
                    name = item.name,
                    address = item.address,
                    star = item.rate,
                    price = item.price,
                    period = item.rentPeriod,
                    image = painterResource(id = item.image),
                    modifier = Modifier
                        .clickable(onClick = onItemClick)
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HuniComposeTheme {
        HomeScreen(onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    HuniComposeTheme {
        HomeHeader(userName = "Naufintya", location = "Bali, Indonesia")
    }
}

@Preview(showBackground = true)
@Composable
fun HuniCategoriesPreview() {
    HuniComposeTheme {
        HuniCategories()
    }
}

@Preview(showBackground = true)
@Composable
fun HuniNearbyLocationsPreview() {
    HuniComposeTheme {
        HuniNearbyLocations(onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HuniPopularPreview() {
    HuniComposeTheme {
        HuniPopular(onItemClick = {})
    }
}