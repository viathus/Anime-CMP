package core.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val jaguar = Color(0xff100b1c)
val topaz = Color(0xFF00C2B7)
val white = Color(0xFFFFFFFF)
val doveGrey = Color(0xFF6D6D6D)

data class AnimeColors(
    val primary: Color = jaguar,
    val onPrimary: Color = white,
    val secondary: Color = topaz,
    val onSecondary: Color = white,
    val divider: Color = doveGrey
)

val LocalColors = staticCompositionLocalOf {
    AnimeColors()
}