package id.eureka.hunicompose.core.util

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.eureka.hunicompose.R
import id.eureka.hunicompose.core.theme.HuniComposeTheme
import id.eureka.hunicompose.core.theme.KanitFont

@Composable
fun GradientButton(
    title: String,
    titleColor: Color,
    titleStyle: TextStyle,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .heightIn(min = ButtonDefaults.MinHeight)
                .fillMaxWidth()
                .background(gradient)
                .padding(vertical = 12.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = title, color = titleColor, style = titleStyle, letterSpacing = 0.3.sp)
        }
    }
}

@Composable
fun HuniCategory(
    title: String,
    titleColor: Color,
    icon: ImageVector?,
    iconFromDrawable: Painter?,
    iconColor: Color,
    modifier: Modifier = Modifier,
) {
    Card(shape = RoundedCornerShape(12.dp), elevation = 10.dp, modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                        .size(32.dp)
                )
            }

            if (iconFromDrawable != null) {
                Icon(
                    painter = iconFromDrawable,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                        .size(32.dp)
                )
            }

            Text(
                text = title,
                fontFamily = KanitFont,
                fontWeight = FontWeight.Light,
                color = titleColor,
                fontSize = 10.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    BasicTextField(modifier = modifier
        .background(
            color = colorResource(id = R.color.white_smoke),
            shape = RoundedCornerShape(12.dp),
        )
        .fillMaxWidth(),
        value = text,
        onValueChange = onTextChanged,
        singleLine = true,
        textStyle = MaterialTheme.typography.h4.copy(
            fontSize = 12.sp
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {
                    if (text.isEmpty()) Text(
                        hint,
                        color = colorResource(id = R.color.storm_dust),
                        style = MaterialTheme.typography.h4,
                        fontSize = 12.sp
                    )

                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String,
    query: String,
    onTextChanged: (String) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        CustomTextField(
            text = query,
            hint = hint,
            onTextChanged = onTextChanged,
            modifier = Modifier
                .weight(9f)
                .padding(vertical = 10.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = colorResource(id = R.color.deep_sapphire),
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(18.dp)
                )
            }
        )

        Spacer(modifier = Modifier.padding(end = 12.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.deep_sapphire)),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(),
            modifier = Modifier.weight(1f),
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    modifier = Modifier
                        .height(18.dp)
                        .width(16.dp)
                )
            }
        }
    }
}

@Composable
fun SectionWithTitleAndSeeAll(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    start = 24.dp,
                    end = 24.dp
                )
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
                fontSize = 16.sp,
                color = colorResource(id = R.color.onyx)
            )

            Text(
                text = "See All",
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp,
                color = colorResource(
                    id = R.color.deep_sapphire
                )
            )
        }

        content()
    }
}

@Composable
fun SectionWithTitle(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            fontSize = 14.sp,
            color = colorResource(id = R.color.onyx),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    start = 24.dp,
                    end = 24.dp
                )
        )

        content()
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Preview(showBackground = true)
@Composable
fun HuniCategoryPreview() {
    HuniComposeTheme {
        HuniCategory(
            title = "Apartment",
            titleColor = colorResource(id = R.color.deep_sapphire),
            icon = null,
            iconFromDrawable = painterResource(id = R.drawable.hotel),
            iconColor = colorResource(id = R.color.deep_sapphire),
        )
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    showMoreText: String = "...Read More",
    showMoreStyle: SpanStyle = SpanStyle(fontFamily = KanitFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorResource(
            id = R.color.deep_sapphire)),
    showLessText: String = "Read Less",
    showLessStyle: SpanStyle = SpanStyle(fontFamily = KanitFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorResource(
            id = R.color.deep_sapphire)),
) {

    var isExpanded by remember { mutableStateOf(false) }
    var isClickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }
    val animateReadMore by animateIntAsState(targetValue = if (isExpanded) Int.MAX_VALUE else 4)

    Box(
        modifier = modifier
            .clickable { isExpanded = !isExpanded }
            .padding(horizontal = 24.dp),
    ) {
        val annotatedText = buildAnnotatedString {
            if (isClickable) {
                if (isExpanded) {
                    append(text)
                    withStyle(style = showLessStyle) { append(showLessText) }
                } else {
                    val adjustText =
                        text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                    append(adjustText)
                    withStyle(style = showMoreStyle) { append(showMoreText) }
                }
            } else {
                append(text)
            }
        }

        Text(
            text = annotatedText,
            style = MaterialTheme.typography.h4,
            color = colorResource(id = R.color.silver_chalice),
            fontSize = 12.sp,
            maxLines = animateReadMore,
            textAlign = TextAlign.Justify,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    isClickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(4 - 1)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    HuniComposeTheme {
        SearchBar(hint = "Search Places", query = "", onTextChanged = {})
    }
}