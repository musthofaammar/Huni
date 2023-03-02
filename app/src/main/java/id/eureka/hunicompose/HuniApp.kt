package id.eureka.hunicompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import id.eureka.hunicompose.core.routes.NavigationItem
import id.eureka.hunicompose.core.routes.Screen
import id.eureka.hunicompose.destinations.DetailHuniScreenDestination
import id.eureka.hunicompose.destinations.HomeScreenDestination
import id.eureka.hunicompose.destinations.OnBoardingScreenDestination
import id.eureka.hunicompose.destinations.SplashScreenDestination

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HuniApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val currentRoute = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    val listNoBottomBar = remember {
        listOf(DetailHuniScreenDestination, SplashScreenDestination, OnBoardingScreenDestination)
    }

    Scaffold(
        bottomBar = { if (currentRoute !in listNoBottomBar) BottomBar(navController) },
        modifier = modifier
    ) { innerPadding ->

        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier
                .padding(innerPadding)
                .semantics {
                    testTagsAsResourceId = true
                })
    }

//        NavHost(
//            navController = navController,
//            startDestination = Screen.Splash.route,
//            modifier = Modifier
//                .padding(innerPadding)
//                .semantics {
//                    testTagsAsResourceId = true
//                }
//        ) {
//            composable(Screen.Splash.route) {
//                SplashScreen(goToNextScreen = {
//                    navController.navigate(Screen.OnBoarding.route) {
//                        popUpTo(Screen.Splash.route) {
//                            inclusive = true
//                        }
//                    }
//                })
//            }
//            composable(Screen.OnBoarding.route) {
//                OnBoardingScreen(goToNextScreen = {
//                    navController.navigate(Screen.Home.route) {
//                        popUpTo(Screen.OnBoarding.route) {
//                            inclusive = true
//                        }
//                    }
//                })
//            }
//            composable(Screen.Home.route) {
//                HomeScreen(onItemClick = {
//                    navController.navigate(Screen.DetailHuni.route) {
//                        popUpTo(Screen.Home.route)
//                    }
//                })
//            }
//            composable(Screen.DetailHuni.route) {
//                DetailHuniScreen(onBack = {
//                    navController.navigateUp()
//                })
//            }
//        }
}


@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        backgroundColor = Color.White, elevation = 10.dp, modifier = modifier
    ) {

        val currentDestination = navController.appCurrentDestinationAsState().value
            ?: NavGraphs.root.startAppDestination

        val navigationItems = listOf(
            NavigationItem(HomeScreenDestination, Icons.Default.Home, Screen.Home),
            NavigationItem(HomeScreenDestination, Icons.Default.Chat, Screen.Message),
            NavigationItem(HomeScreenDestination, Icons.Default.WorkOutline, Screen.Post),
            NavigationItem(HomeScreenDestination, Icons.Default.Bookmark, Screen.Bookmark),
            NavigationItem(HomeScreenDestination, Icons.Default.Person, Screen.Profile),
        )

        navigationItems.map { item ->
            BottomNavigationItem(icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = colorResource(id = R.color.deep_sapphire)
                )
            }, selected = currentDestination == item.direction, onClick = {
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