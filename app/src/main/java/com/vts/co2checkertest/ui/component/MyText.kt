package com.vts.co2checkertest.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vts.co2checkertest.R
import com.vts.co2checkertest.ui.theme.Gray

@Composable
fun CustomText(
    distance: String,
    unit: String,
    mainSize: Int,
    secondarySize: Int
) {
    val mainTextStyle = SpanStyle (
        color = Gray,
        fontSize = mainSize.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semibold))
    )

    val secondaryTextStyle = SpanStyle (
        color = Gray,
        fontSize = secondarySize.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_semibold))
    )

    val annotatedString = buildAnnotatedString {
        withStyle(style = mainTextStyle) {
            append(distance)
        }
        append(" ")
        withStyle(style = secondaryTextStyle) {
            append(unit)
        }
    }
    
    Text(text = annotatedString, modifier = Modifier.padding(bottom = 0.dp))
}