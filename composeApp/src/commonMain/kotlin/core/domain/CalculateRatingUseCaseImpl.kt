package core.domain

import Formatter
import core.model.Rating

internal class CalculateRatingUseCaseImpl(val formatter: Formatter) : CalculateRatingUseCase {
    override fun invoke(score: Double): Rating {
        val starRating = (score / 20).coerceIn(0.0, 5.0)
        val filledStars = starRating.toInt()
        val halfStar = starRating - filledStars >= 0.5
        val emptyStars = (5 - filledStars) - if (halfStar) 1 else 0

        return Rating(
            rating = formatter.format(score),
            filledStars = filledStars,
            emptyStars = emptyStars,
            halfStar = halfStar,
        )
    }
}