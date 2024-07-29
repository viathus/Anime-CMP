package core.domain

import core.model.Rating

interface CalculateRatingUseCase {
    operator fun invoke(score: Double): Rating
}