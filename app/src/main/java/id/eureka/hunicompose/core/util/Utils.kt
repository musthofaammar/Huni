package id.eureka.hunicompose.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Wifi
import id.eureka.hunicompose.R
import id.eureka.hunicompose.detailhuni.data.model.Facilities
import id.eureka.hunicompose.home.presentation.HuniRentPeriod
import id.eureka.hunicompose.home.presentation.model.Huni
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random

object Utils {

    fun numberFormatter(number: Double): String {
        val formatted = DecimalFormat("#,###")
        return formatted.format(number)
    }

    fun dummyHuniItem(): List<Huni> {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        return listOf(
            Huni(
                1,
                name = "Griya Asri Cempaka Raya",
                address = "Jl. Tukad Balian, Renon, No.78",
                image = R.drawable.hotel_1,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 24000000.0,
                rentPeriod = HuniRentPeriod.Month
            ),
            Huni(
                2,
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_2,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
            Huni(
                3,
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_1,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
            Huni(
                4,
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_2,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
            Huni(
                5,
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_1,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
            Huni(
                6,
                name = "Hotel Autumn Center",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_2,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
            Huni(
                7,
                name = "Hotel Autumn Center 5",
                address = "Jl. Bedahulu X No.29",
                image = R.drawable.hotel_1,
                rate = df.format(Random.nextDouble(1.0, 5.0)).toDouble(),
                price = 1600000.0,
                rentPeriod = HuniRentPeriod.Day
            ),
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