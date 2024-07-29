package app

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import core.designsystem.theme.AnimeTheme
import core.common.getAsyncImageLoader
import features.animedetails.navigation.animeDetailScreen
import features.animedetails.navigation.navigateToAnimeDetailScreen
import features.home.navigation.HomeRoute
import features.home.navigation.homeScreen
import features.seemore.navigation.navigateToSeeMore
import features.seemore.navigation.seeMoreScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class, ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    KoinContext {
        setSingletonImageLoaderFactory { context -> getAsyncImageLoader(context) }
        AnimeTheme {
            SharedTransitionLayout {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = HomeRoute,
                    modifier = Modifier.fillMaxSize()
                ) {
                    homeScreen(
                        onSeeMoreClick = navController::navigateToSeeMore,
                        onAnimeClick = navController::navigateToAnimeDetailScreen,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )

                    seeMoreScreen(
                        onNavigateBack = navController::navigateUp,
                        onAnimeCardClick = navController::navigateToAnimeDetailScreen,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )

                    animeDetailScreen(
                        onNavigateBack = navController::navigateUp,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}
