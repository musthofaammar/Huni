package id.eureka.hunicompose.home.presentation.model

import android.os.Parcelable
import id.eureka.hunicompose.detailhuni.data.model.Facilities
import id.eureka.hunicompose.detailhuni.data.model.Review
import kotlinx.parcelize.Parcelize

@Parcelize
data class Huni(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val images: List<Int>,
    val rate: Double,
    val price: Double,
    val rentPeriod: String,
    val ownerName: String,
    val facilities: List<Facilities>,
    val reviews: List<Review>
) : Parcelable
