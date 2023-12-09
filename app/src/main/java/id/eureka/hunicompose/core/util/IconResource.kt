package id.eureka.hunicompose.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

object IconResource {

    private val resources: MutableMap<String, ImageVector> = mutableMapOf()

    init {
        resources["Gym"] = Icons.Filled.SportsGymnastics
        resources["Bedrooms"] = Icons.Filled.Bed
        resources["Free Wifi"] = Icons.Filled.Wifi
        resources["Parking Spots"] = Icons.Filled.CarRental
        resources["Security"] = Icons.Filled.Security
        resources["Smoke Free"] = Icons.Filled.SmokeFree
        resources["Swimming Pool"] = Icons.Filled.Pool
    }

    fun getResource(resourceName: String): ImageVector {
        return resources[resourceName] ?: Icons.Filled.Error
    }
}