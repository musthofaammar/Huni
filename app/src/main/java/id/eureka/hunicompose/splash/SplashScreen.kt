package id.eureka.hunicompose.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.primary)){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(132.dp)
                .height(84.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    HuniComposeTheme {
        SplashScreen()
    }
}