package id.eureka.hunicompose.detailhuni.data.model

import android.os.Parcelable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Facilities(
    val icon: @RawValue ImageVector? = null,
    val iconImage: Int? = null,
    val count: Int,
    val type: String,
) : Parcelable
