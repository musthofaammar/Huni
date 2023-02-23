package id.eureka.hunicompose.home.presentation.model

import id.eureka.hunicompose.home.presentation.HuniRentPeriod

//TODO create mapper

data class Huni(
    val id: Int,
    val name: String,
    val address: String,
    val image: Int,
    val rate: Double,
    val price: Double,
    val rentPeriod: HuniRentPeriod,
)
