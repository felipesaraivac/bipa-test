package com.saraiva.bipa.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = light_primary,
    secondary = light_secondary,
    tertiary = light_tertiary,
    background = light_background,
    surface = light_surface,
    onPrimary = light_onPrimary,
    onSecondary = light_onSecondary,
    onTertiary = light_onTertiary,
    onBackground = light_onBackground,
    onSurface = light_onSurface,
)

private val LightColorScheme = lightColorScheme(
    primary = dark_primary,
    secondary = dark_secondary,
    tertiary = dark_tertiary,
    background = dark_background,
    surface = dark_surface,
    onPrimary = dark_onPrimary,
    onSecondary = dark_onSecondary,
    onTertiary = dark_onTertiary,
    onBackground = dark_onBackground,
    onSurface = dark_onSurface,

)

@Composable
fun BipaTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = colorScheme.surface, darkIcons = !darkTheme)
        systemUiController.setNavigationBarColor(
            color = colorScheme.background,
            darkIcons = !darkTheme,
            navigationBarContrastEnforced = false
        )
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}