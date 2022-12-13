package id.eureka.hunicompose.core.util

import id.eureka.hunicompose.R
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

}