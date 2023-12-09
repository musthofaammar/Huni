package id.eureka.hunicompose.home.presentation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class HuniCategoryData(
    val title : String,
    val icon : ImageVector? = null,
    val iconFromDrawable : Painter? = null
)