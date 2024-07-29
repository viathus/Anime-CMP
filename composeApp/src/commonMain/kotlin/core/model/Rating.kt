package core.model

data class Rating(
    val rating: String,
    val filledStars: Int,
    val emptyStars: Int,
    val halfStar: Boolean,
)