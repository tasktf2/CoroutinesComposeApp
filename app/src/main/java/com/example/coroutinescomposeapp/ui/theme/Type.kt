package com.example.coroutinescomposeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.coroutinescomposeapp.R

val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.montserrat_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.montserrat_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.montserrat_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.montserrat_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.montserrat_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.montserrat_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.montserrat_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.montserrat_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.montserrat_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.montserrat_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.montserrat_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.montserrat_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.montserrat_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.montserrat_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.montserrat_black_italic, FontWeight.Black, FontStyle.Italic)
)


// Set of Material typography styles to start with
val Typography = Typography(
    //main header text
    h1 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),
    //name/profile header
    h2 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    //signup with
    h4 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    //textFields
    h5 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    ),
    //already have an account?
    h6 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    ),
    //textButtons
    body1 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 13.sp
    ),
    button = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp
    )
)