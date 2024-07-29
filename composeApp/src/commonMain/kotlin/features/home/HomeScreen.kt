package features.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.most_popular_home
import animecmp.composeapp.generated.resources.trending_home
import animecmp.composeapp.generated.resources.upcoming_home
import core.designsystem.theme.LocalColors
import core.model.SeeMoreOptions
import core.ui.AnimeRow
import core.ui.TopBar
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    uiState: HomeUiState,
    onSeeMoreClick: (SeeMoreOptions) -> Unit,
    onAnimeCardClick: (id: String) -> Unit,
    animatedContentScope: AnimatedContentScope,
) {
    Column(modifier = Modifier.fillMaxSize().background(color = LocalColors.current.primary)) {
        TopBar()

        Column(
            modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            AnimeRow(
                stringResource(Res.string.most_popular_home),
                uiState.mostPopular,
                { onSeeMoreClick(SeeMoreOptions.MOST_POPULAR) },
                { onAnimeCardClick(it) },
                animatedContentScope,
            )

            Spacer(modifier = Modifier.height(12.dp))

            AnimeRow(
                stringResource(Res.string.trending_home),
                uiState.trending,
                { onSeeMoreClick(SeeMoreOptions.TRENDING) },
                { onAnimeCardClick(it) },
                animatedContentScope,
            )

            Spacer(modifier = Modifier.height(12.dp))

            AnimeRow(
                stringResource(Res.string.upcoming_home),
                uiState.upcoming,
                { onSeeMoreClick(SeeMoreOptions.UPCOMING) },
                { onAnimeCardClick(it) },
                animatedContentScope,
            )
        }
    }
}
