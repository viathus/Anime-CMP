package features.seemore

import features.shareduistate.AnimeUiState

data class SeeMoreUiState(
    val anime: List<AnimeUiState> = emptyList(),
    val isLoading: Boolean = false,
)
