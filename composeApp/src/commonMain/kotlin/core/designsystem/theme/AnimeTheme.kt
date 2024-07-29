package core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AnimeTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides AnimeColors(),
        LocalTypography provides getAnimeTypographyTheme(),
        LocalShapes provides AnimeShapes(),
        content = content
    )
}