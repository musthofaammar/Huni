package id.eureka.hunicompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.eureka.hunicompose.core.routes.NavigationItem
import id.eureka.hunicompose.core.routes.Screen
import id.eureka.hunicompose.detailhuni.DetailHuniScreen
import id.eureka.hunicompose.home.HomeScreen

@Composable
fun HuniApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { if (currentRoute != Screen.DetailHuni.route) BottomBar(navController) },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onItemClick = {
                    navController.navigate(Screen.DetailHuni.route)
                })
            }
            composable(Screen.DetailHuni.route) {
                DetailHuniScreen()
            }
        }
    }
}


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        backgroundColor = Color.White, elevation = 10.dp, modifier = modifier
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(Icons.Default.Home, Screen.Home),
            NavigationItem(Icons.Default.Chat, Screen.Message),
            NavigationItem(Icons.Default.WorkOutline, Screen.Post),
            NavigationItem(Icons.Default.Bookmark, Screen.Bookmark),
            NavigationItem(Icons.Default.Person, Screen.Profile),
        )

        navigationItems.map { item ->
            BottomNavigationItem(icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = colorResource(id = R.color.deep_sapphire)
                )
            }, selected = currentRoute == item.screen.route, onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        }
    }
}