package id.eureka.hunicompose.detailhuni.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Facilities(
    val icon: ImageVector? = null,
    val iconImage: Int? = null,
    val count: Int,
    val type: String,
)
