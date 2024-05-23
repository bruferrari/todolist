package com.ferrarib.todolist.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.unit.dp
import com.ferrarib.todolist.ui.tokens.TypographyTokens

val Typography = Typography(
    bodySmall = TypographyTokens.bodySmall,
    bodyMedium = TypographyTokens.bodyMedium,
    bodyLarge = TypographyTokens.bodyLarge,
    titleMedium = TypographyTokens.titleMedium,
    titleLarge = TypographyTokens.titleLarge,
    labelSmall = TypographyTokens.labelSmall
)

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(32.dp),
    extraLarge = RoundedCornerShape(48.dp)
)