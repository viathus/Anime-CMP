package features.shareduistate

data class RatingUiState(
    val rating: String = "",
    val filledStars: Int = 0,
    val emptyStars: Int = 0,
    val halfStar: Boolean = false,
)
