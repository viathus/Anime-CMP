package core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class AnimeShapes(
    val medium: RoundedCornerShape = RoundedCornerShape(10.dp),
)

val LocalShapes = staticCompositionLocalOf {
    AnimeShapes()
}