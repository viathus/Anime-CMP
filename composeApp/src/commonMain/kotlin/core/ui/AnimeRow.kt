package core.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.see_more
import core.designsystem.theme.LocalColors
import core.designsystem.theme.LocalTypography
import features.shareduistate.AnimeUiState
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AnimeRow(
    title: String,
    state: List<AnimeUiState>,
    seeMoreClick: () -> Unit,
    onAnimeClick: (id: String) -> Unit,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxWidth().height(310.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                style = LocalTypography.current.subtitle,
                color = LocalColors.current.onPrimary,
                text = title,
            )

            Text(
                style = LocalTypography.current.descriptionHome,
                color = LocalColors.current.secondary,
                text = stringResource(Res.string.see_more),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(6.dp).clickable { seeMoreClick() },
            )
        }

        Divider(color = LocalColors.current.divider.copy(alpha = 0.2f))

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(state = listState, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            items(state, key = { anime -> anime.id }) {
                AnimeCard(
                    it,
                    animatedContentScope,
                    modifier = Modifier.width(150.dp),
                    onAnimeClick,
                )
            }
        }
    }
}
