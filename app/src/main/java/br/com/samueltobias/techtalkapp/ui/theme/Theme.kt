package br.com.samueltobias.techtalkapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = PantoneYellow,
    onPrimary = PantoneBlack,
    background = PantoneYellow,
    onBackground = PantoneBlack,
    surface = PantoneWhite,
    onSurface = PantoneBlack,
    secondary = PantoneWhite,
    onSecondary = PantoneBlack
)

@Composable
fun TechTalkAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}