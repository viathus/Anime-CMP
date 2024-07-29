package features.home

import features.shareduistate.AnimeUiState

data class HomeUiState(
    val mostPopular: List<AnimeUiState> = emptyList(),
    val trending: List<AnimeUiState> = emptyList(),
    val upcoming: List<AnimeUiState> = emptyList(),
    val isLoading: Boolean = false,
)
