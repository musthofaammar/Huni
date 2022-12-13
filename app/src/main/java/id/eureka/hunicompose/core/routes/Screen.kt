package id.eureka.hunicompose.core.routes

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Message : Screen("message")
    object Post : Screen("post")
    object Bookmark : Screen("bookmark")
    object Profile : Screen("profile")
    object DetailHuni : Screen("detailhuni")
}