package id.eureka.hunicompose.nextfeature

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun NestedLazyColumn() {
    val outerList = (1..10).toList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(outerList) { outerItem ->
            Text(
                text = "Outer Item $outerItem",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Inner LazyColumn
            val innerList = (1..5).toList()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(innerList) { innerItem ->
                    Text(
                        text = "Inner Item $innerItem",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
