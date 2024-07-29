package features.animedetails.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import features.animedetails.AnimeDetailScreen
import features.animedetails.AnimeDetailViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@Serializable
data class AnimeDetailRoute(
    val id: String
)

fun NavController.navigateToAnimeDetailScreen(id: String) =
    navigate(AnimeDetailRoute(id))

@OptIn(KoinExperimentalAPI::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.animeDetailScreen(
    onNavigateBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    composable<AnimeDetailRoute> { backStackEntry ->
        val viewModel: AnimeDetailViewModel = koinViewModel<AnimeDetailViewModel> {
            parametersOf(
                SavedStateHandle.createHandle(
                    null,
                    backStackEntry.arguments
                )
            )
        }

        val uiState = viewModel.anime.collectAsStateWithLifecycle()
        with(sharedTransitionScope) {
            AnimeDetailScreen(
                uiState = uiState.value,
                onNavigateBack = onNavigateBack,
                animatedContentScope = this@composable
            )
        }
    }
}