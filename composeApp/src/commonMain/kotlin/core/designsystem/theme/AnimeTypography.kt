package core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import animecmp.composeapp.generated.resources.Poppins_Bold
import animecmp.composeapp.generated.resources.Poppins_Regular
import animecmp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

data class AnimeTypographyTheme(
    val largeTitle: TextStyle = TextStyle.Default,
    val subtitle: TextStyle = TextStyle.Default,
    val descriptionTitle: TextStyle = TextStyle.Default,
    val description: TextStyle = TextStyle.Default,
    val descriptionHome: TextStyle = TextStyle.Default,
)

@Composable
fun getAnimeTypographyTheme(): AnimeTypographyTheme {
    val poppinsFontFamily = FontFamily(
        Font(Res.font.Poppins_Regular, FontWeight.Normal), // 400
        Font(Res.font.Poppins_Bold, FontWeight.Bold), // 700
    )

    return AnimeTypographyTheme(
        largeTitle = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W700,
            lineHeight = 36.sp,
            fontSize = 24.sp
        ),
        subtitle = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W700,
            lineHeight = 21.sp,
            fontSize = 14.sp
        ),
        descriptionTitle = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W700,
            lineHeight = 24.sp,
            fontSize = 16.sp
        ),
        description = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W400,
            lineHeight = 18.sp,
            fontSize = 12.sp
        ),
        descriptionHome = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.W700,
            lineHeight = 18.sp,
            fontSize = 12.sp
        )
    )
}

val LocalTypography = staticCompositionLocalOf {
    AnimeTypographyTheme()
}
