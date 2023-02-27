package id.eureka.hunicompose.core.routes

import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

data class NavigationItem(
    val direction : DirectionDestinationSpec,
    val icon: ImageVector,
    val screen: Screen,
)
