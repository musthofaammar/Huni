package id.eureka.hunicompose.detailhuni

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import kotlin.random.Random

@Composable
fun ReviewsTab(
    modifier: Modifier = Modifier,
    viewModel: ReviewViewModel = viewModel()
) {

    val filterReviewSelected by viewModel.selectedRate.collectAsState()
    val reviews by viewModel.filteredReviews.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(6, key = { it }) { position ->
                FilterReviewItem(
                    isShowAll = position == 0,
                    isShowAllText = "All Reviews",
                    rate = 5 - position,
                    isSelected = filterReviewSelected == position,
                    reviewCount = Random.nextInt(1, 100),
                    onFilterReviewClick = {
                        viewModel.setSelectedRate(position)
                    }
                )
            }
        }

        for (i in reviews.indices) {
            ReviewItem(
                name = reviews[i].name,
                rate = reviews[i].rate,
                description = reviews[i].description,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}

@Composable
fun FilterReviewItem(
    modifier: Modifier = Modifier,
    isShowAll: Boolean,
    isSelected: Boolean,
    rate: Int,
    isShowAllText: String = "",
    reviewCount: Int,
    onFilterReviewClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = colorResource(id = R.color.deep_sapphire)),
        backgroundColor = if (isSelected) colorResource(id = R.color.deep_sapphire) else Color.White,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(24.dp)
                .clickable { onFilterReviewClick() },
        ) {
            if (isShowAll)
                Text(
                    text = isShowAllText,
                    style = MaterialTheme.typography.h3,
                    color = if (isSelected) Color.White else colorResource(id = R.color.deep_sapphire),
                    fontSize = 12.sp
                )
            else
                Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                    for (i in 0 until 5) {
                        if (i <= rate) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = colorResource(id = R.color.dark_yellow),
                                modifier = Modifier.size(12.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = colorResource(id = R.color.porcelain),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }

            Text(
                text = "(${reviewCount})",
                style = MaterialTheme.typography.h4,
                color = colorResource(id = R.color.silver_chalice),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun ReviewItem(
    name: String,
    rate: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.h3,
                color = colorResource(id = R.color.deep_sapphire),
                fontSize = 12.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                for (i in 0 until rate) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = colorResource(id = R.color.dark_yellow),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

//            ExpandableText(
//                text = description,
//                textStyle = MaterialTheme.typography.h4.copy(
//                    fontSize = 10.sp,
//                    color = colorResource(id = R.color.onyx)
//                )
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewsTabPreview() {
    HuniComposeTheme {
        ReviewsTab()
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewItemPreview() {
    HuniComposeTheme {
        ReviewItem(
            name = "Karina Yoo",
            rate = 4,
            description = "I have only ever had good experiences with Rent House. The owner and team are friendly and helpful, they treat us with respect and I've always felt like hula hula kaw kaw",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterReviewItemWithShowAllPreview() {
    HuniComposeTheme {
        FilterReviewItem(
            isShowAll = true,
            rate = 0,
            isShowAllText = "All Reviews",
            reviewCount = 3,
            isSelected = true,
            onFilterReviewClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterReviewItemWithStarPreview() {
    HuniComposeTheme {
        FilterReviewItem(
            isShowAll = false,
            rate = 3,
            isShowAllText = "All Reviews",
            reviewCount = 10,
            isSelected = false,
            onFilterReviewClick = {}
        )
    }
}