package id.eureka.hunicompose.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.shimmer
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.core.util.shadow

@Composable
fun HuniItemShort(
    name: String,
    address: String,
    star: Double,
    price: Double,
    period: HuniRentPeriod,
    image: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(106.dp)
                    .width(180.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(8.3f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .size(12.dp)
                        )

                        Text(
                            text = address,
                            style = MaterialTheme.typography.h4,
                            color = colorResource(id = R.color.silver_chalice),
                            fontSize = 8.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.weight(1.7f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.dark_yellow),
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 2.dp)
                        )

                        Text(
                            text = String.format("%.1f", star),
                            style = MaterialTheme.typography.h4,
                            color = colorResource(id = R.color.silver_chalice),
                            fontSize = 8.sp
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "IDR ${Utils.numberFormatter(price)}/${period.period}",
                        style = MaterialTheme.typography.h3,
                        color = colorResource(id = R.color.deep_sapphire),
                        fontSize = 10.sp
                    )
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = null,
                        tint = colorResource(id = R.color.deep_sapphire),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HuniItemShortLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(
                colorResource(id = R.color.storm_dust),
                borderRadius = 12.dp,
                blurRadius = 8.dp,
            )
            .width(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .shimmer(),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(106.dp)
                    .width(180.dp)
                    .background(colorResource(id = R.color.storm_dust))
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(colorResource(id = R.color.storm_dust))
                )

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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(colorResource(id = R.color.storm_dust))
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.dark_yellow),
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 2.dp)
                        )

                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .height(16.dp)
                                .background(colorResource(id = R.color.storm_dust))
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(colorResource(id = R.color.storm_dust))
                    )
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = null,
                        tint = colorResource(id = R.color.deep_sapphire),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HuniItemLong(
    name: String,
    address: String,
    star: Double,
    price: Double,
    period: HuniRentPeriod,
    image: Painter,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.height(80.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.onyx),
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(8.5f)
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
                            fontSize = 8.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.weight(1.5f)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.dark_yellow),
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 2.dp)
                        )

                        Text(
                            text = String.format("%.1f", star),
                            style = MaterialTheme.typography.h4,
                            color = colorResource(id = R.color.silver_chalice),
                            fontSize = 8.sp
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "IDR ${Utils.numberFormatter(price)}/${period.period}",
                        style = MaterialTheme.typography.h3,
                        color = colorResource(id = R.color.deep_sapphire),
                        fontSize = 10.sp
                    )
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = null,
                        tint = colorResource(id = R.color.deep_sapphire),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HuniItemLongLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(
                colorResource(id = R.color.storm_dust),
                borderRadius = 12.dp,
                blurRadius = 8.dp,
            )
            .height(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .shimmer(),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(72.dp)
                    .background(colorResource(id = R.color.storm_dust))
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(colorResource(id = R.color.storm_dust))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(colorResource(id = R.color.storm_dust))
                )

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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                                .background(colorResource(id = R.color.storm_dust))
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = colorResource(id = R.color.dark_yellow),
                            modifier = Modifier
                                .size(14.dp)
                                .padding(end = 2.dp)
                        )

                        Box(
                            modifier = Modifier
                                .width(8.dp)
                                .height(16.dp)
                                .background(colorResource(id = R.color.storm_dust))
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                            .background(colorResource(id = R.color.storm_dust))
                    )
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = null,
                        tint = colorResource(id = R.color.deep_sapphire),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HuniItemShortPreview() {
    HuniComposeTheme {
        HuniItemShort(
            name = "Griya Asri Cempaka Raya",
            address = "Jl. Tukad Balian, Renon, No. 78",
            star = 4.7,
            price = 24000000.0,
            image = painterResource(id = R.drawable.property_1),
            period = HuniRentPeriod.Month,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun HuniItemLongPreview() {
    HuniComposeTheme {
        HuniItemLong(
            name = "Griya Asri Cempaka Raya",
            address = "Jl. Tukad Balian, Renon, No. 78",
            star = 4.7,
            price = 24000000.0,
            image = painterResource(id = R.drawable.property_1),
            period = HuniRentPeriod.Month
        )
    }
}

@Preview
@Composable
fun HuniItemLongLoadingPreview() {
    HuniComposeTheme {
        HuniItemLongLoading()
    }
}

@Preview
@Composable
fun HuniItemShortLoadingPreview() {
    HuniComposeTheme {
        HuniItemShortLoading()
    }
}

sealed class HuniRentPeriod(val period: String) {
    object Year : HuniRentPeriod("year")
    object SixMonths : HuniRentPeriod("6 months")
    object Month : HuniRentPeriod("month")
    object Day : HuniRentPeriod("day")


}