package id.eureka.hunicompose.detailhuni.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val name: String,
    val rate: Int,
    val description: String,
) : Parcelable
