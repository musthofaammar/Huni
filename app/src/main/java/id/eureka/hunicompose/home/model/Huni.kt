package id.eureka.hunicompose.home.model

import id.eureka.hunicompose.home.HuniRentPeriod

data class Huni(
    val id: Int,
    val name: String,
    val address: String,
    val image: Int,
    val rate: Double,
    val price: Double,
    val rentPeriod: HuniRentPeriod,
)
