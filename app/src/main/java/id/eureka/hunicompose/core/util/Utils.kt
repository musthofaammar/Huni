package id.eureka.hunicompose.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Wifi
import id.eureka.hunicompose.R
import id.eureka.hunicompose.detailhuni.model.Facilities
import id.eureka.hunicompose.home.HuniRentPeriod
import id.eureka.hunicompose.home.model.Huni
import java.text.DecimalFormat

object Utils {

    fun numberFormatter(number: Double): String {
        val formatted = DecimalFormat("#,###")
        return formatted.format(number)
    }

    fun dummyHuniItem(): List<Huni> {
        return listOf(
            Huni(
                name = "Griya Asri Cempaka Raya",
                address = "Jl. Tukad Balian, Renon, No.78",
                image = R.drawable.hotel_1,
                rate = 4.7,
                price = 24000000.0,
                rentPeriod = HuniRentPeriod.Month
            ),
            Huni(
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_2,
                rate = 4.8,
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            )
        )
    }

    fun dummyFacilities(): List<Facilities> {
        return listOf(
            Facilities(icon = Icons.Filled.SportsGymnastics, type = "Gym", count = 1),
            Facilities(iconImage = R.drawable.bed, type = "Bedrooms", count = 5),
            Facilities(icon = Icons.Filled.Wifi, type = "Free Wifi", count = 1),
            Facilities(icon = Icons.Filled.CarRental, type = "Parking Spots", count = 1),
            Facilities(icon = Icons.Filled.Security, type = "Security", count = 1),
        )
    }

}