package features.home.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import core.model.SeeMoreOptions
import features.home.HomeScreen
import features.home.HomeViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
data object HomeRoute

fun NavController.navigateHome() = navigate(HomeRoute)

@OptIn(KoinExperimentalAPI::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeScreen(
    onSeeMoreClick: (seeMoreOptions: SeeMoreOptions) -> Unit,
    onAnimeClick: (id: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
) {
    composable<HomeRoute> {
        val viewModel = koinViewModel<HomeViewModel>()
        val state = viewModel.anime.collectAsStateWithLifecycle()

        sharedTransitionScope.HomeScreen(
            uiState = state.value,
            onSeeMoreClick = onSeeMoreClick,
            onAnimeCardClick = onAnimeClick,
            animatedContentScope = this@composable,
        )
    }
}