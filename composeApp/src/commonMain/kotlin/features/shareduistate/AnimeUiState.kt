package features.shareduistate

data class AnimeUiState(
    val id: String,
    val animeName: String,
    val posterImage: String,
    val rating: RatingUiState,
)