package id.eureka.hunicompose.detailhuni.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.util.ExpandableText
import id.eureka.hunicompose.core.util.IconResource
import id.eureka.hunicompose.core.util.SectionWithTitle
import id.eureka.hunicompose.core.util.Utils
import id.eureka.hunicompose.detailhuni.data.model.Facilities

@Composable
fun DetailHuniTab(
    facilities: List<Facilities>,
    description: String,
    scrollToEnd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        SectionWithTitle(title = "Facilities") {
            FlowRow(
                mainAxisSpacing = 16.dp,
                crossAxisSpacing = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                for (item in facilities) {
                    FacilityItem(
                        count = item.count,
                        type = item.type
                    )
                }
            }
        }

        SectionWithTitle(title = "Description") {
            ExpandableText(
                text = description,
                maxLine = 4,
                modifier = Modifier.padding(horizontal = 24.dp),
                scrollToEnd = scrollToEnd
            )
        }
    }
}

@Composable
fun FacilityItem(
    modifier: Modifier = Modifier,
    count: Int,
    type: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = IconResource.getResource(type),
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
            scrollToEnd = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FacilityItemPreview() {
    HuniComposeTheme {
        FacilityItem(count = 5, type = "Bedrooms")
    }
}