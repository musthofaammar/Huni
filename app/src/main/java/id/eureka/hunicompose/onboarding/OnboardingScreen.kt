package id.eureka.hunicompose.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.theme.KanitFont
import id.eureka.hunicompose.core.util.GradientButton

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    goToNextScreen: () -> Unit
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val height = (LocalConfiguration.current.screenHeightDp * 0.65).dp
        val offset = (LocalConfiguration.current.screenHeightDp * 0.6).dp
        Image(
            painter = painterResource(id = R.drawable.onboarding),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        )
        Column(
            Modifier
                .offset(y = offset)
                .padding(horizontal = 16.dp)
        ) {

            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource(id = R.color.onyx))) {
                    append("Find your ")
                }

                withStyle(style = SpanStyle(color = colorResource(id = R.color.deep_sapphire))) {
                    append("cozy place \n")
                }

                withStyle(style = SpanStyle(color = colorResource(id = R.color.onyx))) {
                    append("to ")
                }

                withStyle(style = SpanStyle(color = colorResource(id = R.color.deep_sapphire))) {
                    append("stay")
                }
            }

            Text(
                text = annotatedText,
                fontSize = 24.sp,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )

            Text(
                text = "Sell, buy, or rent any properties easily through Hunian. Find out the culture regarding your destination.",
                fontSize = 16.sp,
                fontFamily = KanitFont,
                fontWeight = FontWeight.Light,
                color = colorResource(id = R.color.silver_chalice),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 48.dp)
            )

            GradientButton(
                title = "Get Started",
                titleColor = Color.White,
                titleStyle = MaterialTheme.typography.h3,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.denim),
                        colorResource(id = R.color.deep_sapphire),
                    ),
                ),
                onClick = goToNextScreen,
                modifier = Modifier.testTag("button_get_started")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    HuniComposeTheme {
        OnBoardingScreen(goToNextScreen = {})
    }
}