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
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.eureka.hunicompose.core.routes.NavigationItem
import id.eureka.hunicompose.core.routes.Screen
import id.eureka.hunicompose.detailhuni.presentation.DetailHuniScreen
import id.eureka.hunicompose.home.presentation.HomeScreen
import id.eureka.hunicompose.onboarding.OnBoardingScreen
import id.eureka.hunicompose.splash.SplashScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HuniApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val listNoBottomBar = remember {
        listOf(Screen.DetailHuni.route, Screen.Splash.route, Screen.OnBoarding.route)
    }

    Scaffold(
        bottomBar = { if (currentRoute !in listNoBottomBar) BottomBar(navController) },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    testTagsAsResourceId = true
                }
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(goToNextScreen = {
                    navController.navigate(Screen.OnBoarding.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(Screen.OnBoarding.route) {
                OnBoardingScreen(goToNextScreen = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(onItemClick = {
                    navController.navigate(Screen.DetailHuni.route) {
                        popUpTo(Screen.Home.route)
                    }
                })
            }
            composable(Screen.DetailHuni.route) {
                DetailHuniScreen(onBack = {
                    navController.navigateUp()
                })
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