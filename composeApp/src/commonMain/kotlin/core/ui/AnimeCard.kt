package core.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import core.designsystem.theme.LocalColors
import core.designsystem.theme.LocalTypography
import features.shareduistate.AnimeUiState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AnimeCard(
    animeUiState: AnimeUiState,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onAnimeClick: (id: String) -> Unit,
) {
    Column(
        modifier = modifier.defaultMinSize(minWidth = 150.dp)
            .clickable { onAnimeClick(animeUiState.id) },
    ) {
        AsyncImage(
            model = animeUiState.posterImage,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(210.dp).sharedBounds(
                sharedContentState = rememberSharedContentState(key = "${animeUiState.id}-poster"),
                animatedVisibilityScope = animatedContentScope,
            ),
            contentScale = ContentScale.FillWidth,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            style = LocalTypography.current.descriptionHome,
            color = LocalColors.current.onPrimary,
            text = animeUiState.animeName,
            modifier = Modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(key = "${animeUiState.id}-text"),
                animatedVisibilityScope = animatedContentScope,
            ),
        )

        Spacer(modifier = Modifier.height(2.dp))

        StarRatingRow(
            animeUiState.rating,
            modifier = Modifier.fillMaxWidth().sharedBounds(
                sharedContentState = rememberSharedContentState(key = "${animeUiState.id}-rating"),
                animatedVisibilityScope = animatedContentScope,
            ),
        )
    }
}