package id.eureka.hunicompose.detailhuni

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowColumn
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.util.ExpandableText
import id.eureka.hunicompose.core.util.SectionWithTitle
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.detailhuni.model.Facilities

@Composable
fun DetailHuniTab(
    facilities: List<Facilities>,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        SectionWithTitle(title = "Facilities") {
            LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Adaptive(92.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(facilities) { item ->
                    FacilityItem(
                        count = item.count,
                        type = item.type,
                        icon = item.icon,
                        iconImage = item.iconImage
                    )
                }
            }
        }

        SectionWithTitle(title = "Description") {
//            ExpandableText(
//                text = description,
//                maxLine = 4,
//                modifier = Modifier.padding(horizontal = 24.dp)
//            )
        }
    }
}

@Composable
fun FacilityItem(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconImage: Int? = null,
    count: Int,
    type: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        if (icon != null)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorResource(id = R.color.silver_chalice),
                modifier = Modifier.size(16.dp)
            )

        if (iconImage != null)
            Icon(
                painter = painterResource(id = iconImage),
                contentDescription = null,
                tint = colorResource(id = R.color.silver_chalice),
                modifier = Modifier.size(16.dp)
            )

        Text(
            text = if (count > 1) "$count $type" else type,
            style = MaterialTheme.typography.h4,
            color = colorResource(id = R.color.silver_chalice),
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailInfoPreview() {
    HuniComposeTheme {
        DetailHuniTab(
            Utils.dummyFacilities(),
            "Located in Denpasar, Bali, this 5-bedroom griya is available for monthly rent. Situated in an exclusive area, this griya is easily accessed by a well-paved road. The property is close to the famous Goemerot Restaurant, White asllasldansjdnj asdnjasndjna hiasdiah askmdkasmkdm nasjdnajsdn",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FacilityItemPreview() {
    HuniComposeTheme {
        FacilityItem(iconImage = R.drawable.bed, count = 5, type = "Bedrooms")
    }
}