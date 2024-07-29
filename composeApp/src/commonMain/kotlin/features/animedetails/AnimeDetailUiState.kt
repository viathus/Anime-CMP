package features.animedetails

import features.shareduistate.RatingUiState

data class AnimeDetailUiState(
    val id: String = "",
    val animeName: String = "",
    val posterImage: String = "",
    val coverImage: String? = null,
    val description: String = "",
    val youtubeUrl: String? = "",
    val rating: RatingUiState = RatingUiState(),
)
