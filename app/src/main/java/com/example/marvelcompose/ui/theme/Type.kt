package com.example.marvelcompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.marvelcompose.R

val prompt = FontFamily(
    Font(R.font.prompt_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.prompt_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.prompt_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.prompt_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.prompt_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.prompt_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.prompt_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.prompt_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.prompt_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.prompt_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.prompt_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.prompt_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.prompt_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.prompt_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.prompt_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.prompt_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.prompt_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.prompt_thinitalic, FontWeight.Thin, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    defaultFontFamily = prompt
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
    */
)