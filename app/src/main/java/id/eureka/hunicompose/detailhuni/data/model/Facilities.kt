package id.eureka.hunicompose.detailhuni.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Facilities(
    val count: Int,
    val type: String,
) : Parcelable
