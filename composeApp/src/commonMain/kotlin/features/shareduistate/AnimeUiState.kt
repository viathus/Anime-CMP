package features.shareduistate

import androidx.compose.runtime.Stable

@Stable
data class AnimeUiState(
    val id: String,
    val animeName: String,
    val posterImage: String,
    val rating: RatingUiState,
)