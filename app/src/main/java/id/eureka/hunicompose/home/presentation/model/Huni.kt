package id.eureka.hunicompose.home.presentation.model

import id.eureka.hunicompose.detailhuni.data.model.Facilities
import id.eureka.hunicompose.detailhuni.data.model.Review
import id.eureka.hunicompose.home.presentation.HuniRentPeriod

//TODO create mapper

data class Huni(
    val id: Int,
    val name: String,
    val description : String,
    val address: String,
    val images: List<Int>,
    val rate: Double,
    val price: Double,
    val rentPeriod: HuniRentPeriod,
    val ownerName : String,
    val facilities: List<Facilities>,
    val reviews : List<Review>
)
