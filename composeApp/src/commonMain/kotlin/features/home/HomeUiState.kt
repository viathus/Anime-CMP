package features.home

import androidx.compose.runtime.Stable
import features.shareduistate.AnimeUiState

@Stable
data class HomeUiState(
    val mostPopular: List<AnimeUiState> = emptyList(),
    val trending: List<AnimeUiState> = emptyList(),
    val upcoming: List<AnimeUiState> = emptyList(),
    val isLoading: Boolean = false,
)
