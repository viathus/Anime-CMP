package core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.baseline_star_half_24
import core.designsystem.theme.LocalColors
import core.designsystem.theme.LocalTypography
import features.shareduistate.RatingUiState
import org.jetbrains.compose.resources.painterResource

@Composable
fun StarRatingRow(rating: RatingUiState, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            repeat(rating.filledStars) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = LocalColors.current.secondary,
                    modifier = Modifier.size(14.dp),
                )
            }
            if (rating.halfStar) {
                Icon(
                    painter = painterResource(Res.drawable.baseline_star_half_24),
                    contentDescription = null,
                    tint = LocalColors.current.secondary,
                    modifier = Modifier.size(14.dp),
                )
            }
            repeat(rating.emptyStars) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = LocalColors.current.secondary.copy(alpha = 0.5f),
                    modifier = Modifier.size(14.dp),
                )
            }

        }
        Text(
            style = LocalTypography.current.description,
            color = LocalColors.current.onPrimary,
            text = rating.rating,
        )
    }
}
