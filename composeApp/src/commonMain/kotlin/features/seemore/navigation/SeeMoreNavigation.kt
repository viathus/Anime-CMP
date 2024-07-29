package features.seemore.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import core.model.SeeMoreOptions
import core.model.SeeMoreOptionsNavType
import features.seemore.SeeMoreScreen
import features.seemore.SeeMoreViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf


@Serializable
data class SeeMoreRoute(
    val seeMoreOptions: SeeMoreOptions
)

fun NavController.navigateToSeeMore(seeMoreOptions: SeeMoreOptions) =
    navigate(SeeMoreRoute(seeMoreOptions))

@OptIn(KoinExperimentalAPI::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.seeMoreScreen(
    onNavigateBack: () -> Unit,
    onAnimeCardClick: (id: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    composable<SeeMoreRoute>(
        typeMap = mapOf(typeOf<SeeMoreOptions>() to SeeMoreOptionsNavType())
    ) { backStackEntry ->
        val seeMoreOptions = backStackEntry.toRoute<SeeMoreRoute>()

        /**
         *  manually setting saved state handle as there seems to be an issue with injecting
         *  saved state handle via koin in compose multiplatform
         */
        val viewModel: SeeMoreViewModel = koinViewModel<SeeMoreViewModel> {
            parametersOf(
                SavedStateHandle.createHandle(
                    null,
                    backStackEntry.arguments
                )
            )
        }

        val uiState = viewModel.anime.collectAsStateWithLifecycle()

        SeeMoreScreen(
            uiState = uiState.value,
            onNavigateBack = onNavigateBack,
            onAnimeCardClick = onAnimeCardClick,
            loadMore = viewModel::loadMore,
            options = seeMoreOptions.seeMoreOptions,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this@composable
        )
    }
}