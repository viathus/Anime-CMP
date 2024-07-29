package features.seemore

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.most_popular_home
import animecmp.composeapp.generated.resources.trending_home
import animecmp.composeapp.generated.resources.upcoming_home
import core.designsystem.theme.LocalColors
import core.designsystem.theme.LocalTypography
import core.model.SeeMoreOptions
import core.ui.AnimeCard
import core.ui.TopBar
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SeeMoreScreen(
    uiState: SeeMoreUiState,
    onNavigateBack: () -> Unit,
    onAnimeCardClick: (id: String) -> Unit,
    loadMore: () -> Unit,
    options: SeeMoreOptions,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    val listState = rememberLazyGridState()

    val refresh by remember {
        derivedStateOf {
            if (listState.layoutInfo.totalItemsCount != 0) {
                !listState.canScrollForward
            } else {
                false
            }
        }
    }

    LaunchedEffect(refresh) {
        if (refresh) {
            loadMore()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(color = LocalColors.current.primary),
    ) {
        TopBar {
            onNavigateBack()
        }

        Column(modifier = Modifier.padding(start = 18.dp, end = 18.dp)) {
            Text(
                style = LocalTypography.current.largeTitle,
                color = LocalColors.current.onPrimary,
                text = when (options) {
                    SeeMoreOptions.TRENDING -> stringResource(Res.string.trending_home)
                    SeeMoreOptions.UPCOMING -> stringResource(Res.string.upcoming_home)
                    SeeMoreOptions.MOST_POPULAR -> stringResource(Res.string.most_popular_home)
                },
            )

            Divider(color = LocalColors.current.divider.copy(alpha = 0.2f))

            Spacer(modifier = Modifier.height(8.dp))

            with(sharedTransitionScope) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    state = listState,
                ) {
                    items(uiState.anime, key = { anime -> anime.id }) { anime ->
                        AnimeCard(anime, animatedContentScope, modifier = Modifier.fillMaxWidth()) {
                            onAnimeCardClick(it)
                        }
                    }

                    if (uiState.isLoading) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(42.dp),
                                    color = LocalColors.current.onPrimary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}