package features.animedetails

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.read_less
import animecmp.composeapp.generated.resources.read_more
import animecmp.composeapp.generated.resources.trailer
import coil3.compose.AsyncImage
import core.common.YoutubeVideoPlayer
import core.designsystem.theme.LocalColors
import core.designsystem.theme.LocalTypography
import core.ui.StarRatingRow
import core.ui.TopBar
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AnimeDetailScreen(
    uiState: AnimeDetailUiState,
    onNavigateBack: () -> Unit,
    animatedContentScope: AnimatedContentScope,
) {
    var expandedState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(color = LocalColors.current.primary),
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(260.dp)) {
            AsyncImage(
                model = uiState.coverImage,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(220.dp),
                contentScale = ContentScale.Crop,
            )

            TopBar(showIcon = false) {
                onNavigateBack()
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp).align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, LocalColors.current.primary.copy(alpha = 0.99f),
                            ),
                            startY = 0f, endY = 100f,
                        ),
                    ),
            )

            Row(
                modifier = Modifier.align(Alignment.BottomStart).padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = uiState.posterImage,
                    contentDescription = null,
                    modifier = Modifier.width(80.dp).height(120.dp).sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "${uiState.id}-poster"),
                        animatedVisibilityScope = animatedContentScope,
                    ),
                )

                Spacer(modifier = Modifier.width(6.dp))

                Column {
                    Text(
                        style = LocalTypography.current.descriptionTitle,
                        color = LocalColors.current.onPrimary,
                        text = uiState.animeName,
                        modifier = Modifier.sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "${uiState.id}-text"),
                            animatedVisibilityScope = animatedContentScope,
                        ),
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        style = LocalTypography.current.descriptionHome,
                        color = LocalColors.current.onPrimary,
                        text = uiState.animeName,
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    StarRatingRow(
                        uiState.rating,
                        modifier = Modifier.width(120.dp).sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "${uiState.id}-rating"),
                            animatedVisibilityScope = animatedContentScope,
                        ),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        AnimeDetailContent(uiState, expandedState) {
            expandedState = !expandedState
        }
    }
}

@Composable
private fun AnimeDetailContent(
    uiState: AnimeDetailUiState,
    expandedState: Boolean,
    onExpanded: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(start = 18.dp, end = 18.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier.animateContentSize()
                .height(IntrinsicSize.Min)
                .fillMaxWidth().clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {
                    onExpanded()
                },
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                Text(
                    style = LocalTypography.current.description,
                    text = uiState.description,
                    color = LocalColors.current.onPrimary,
                    overflow = if (expandedState) TextOverflow.Visible else TextOverflow.Ellipsis,
                    maxLines = if (expandedState) 200 else 4,
                )

                Text(
                    textDecoration = TextDecoration.Underline,
                    style = LocalTypography.current.description,
                    color = LocalColors.current.secondary,
                    text = if (expandedState) stringResource(
                        Res.string.read_less,
                    ) else stringResource(Res.string.read_more),
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (uiState.youtubeUrl != null) {
            Text(
                style = LocalTypography.current.subtitle,
                text = stringResource(Res.string.trailer),
                color = LocalColors.current.onPrimary,
            )

            Divider(color = LocalColors.current.divider.copy(alpha = 0.2f))

            Spacer(modifier = Modifier.height(12.dp))

            YoutubeVideoPlayer(
                modifier = Modifier.fillMaxWidth().height(230.dp),
                url = uiState.youtubeUrl,
                LocalLifecycleOwner.current,
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
