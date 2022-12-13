package id.eureka.hunicompose.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.eureka.hunicompose.R


val DmSerifFontRegular = FontFamily(Font(R.font.dm_serif_regular))

val KanitFont = FontFamily(
    Font(R.font.kanit_regular, weight = FontWeight.Normal),
    Font(R.font.kanit_light, weight = FontWeight.Light),
    Font(R.font.kanit_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.kanit_bold, weight = FontWeight.Bold),
    Font(R.font.kanit_extrabold, weight = FontWeight.ExtraBold),
)

// Set of Material typography styles to start with
val Typography = Typography(body1 = TextStyle(fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp),
    subtitle1 = TextStyle(fontFamily = DmSerifFontRegular, fontWeight = FontWeight.W400),
    subtitle2 = TextStyle(fontFamily = KanitFont, fontWeight = FontWeight.W300),
    h3 = TextStyle(fontFamily = KanitFont, fontWeight = FontWeight.Normal),
    h4 = TextStyle(fontFamily = KanitFont, fontWeight = FontWeight.Light)
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */)