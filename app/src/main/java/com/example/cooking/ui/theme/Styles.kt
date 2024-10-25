package com.example.cooking.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Styles {

    val commonTextStyle = TextStyle(
        fontSize = 16.sp,
        color = Colors.black,
        fontWeight = FontWeight(400)
    )

    val titleTextStyle = commonTextStyle.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight(700)
    )
}