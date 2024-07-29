package features.seemore

import androidx.compose.runtime.Stable
import features.shareduistate.AnimeUiState

@Stable
data class SeeMoreUiState(
    val anime: List<AnimeUiState> = emptyList(),
    val isLoading: Boolean = false,
)
