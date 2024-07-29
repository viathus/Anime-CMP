package core.data.mapper

import core.database.model.AnimeEntity
import core.model.Anime
import core.network.model.AnimeDataResponse

fun AnimeDataResponse.asEntity(page: Int) = AnimeEntity(
    id = idResponse,
    animeName = attributesResponse.titlesResponse?.enResponse
        ?: attributesResponse.titlesResponse?.enJpResponse ?: "unknown",
    posterImage = attributesResponse.posterImageResponse.largeResponse,
    description = attributesResponse.descriptionResponse,
    coverImage = attributesResponse.coverImageResponse?.smallResponse,
    youtubeLink = attributesResponse.youtubeVideoIdResponse,
    rating = attributesResponse.averageRatingResponse ?: "0",
    episodes = attributesResponse.episodeCountResponse,
    page = page
)

fun AnimeEntity.asExternalModel() = Anime(
    id = id,
    animeName = animeName,
    posterImage = posterImage,
    description = description,
    coverImage = coverImage,
    youtubeLink = youtubeLink,
    rating = rating,
    episodes = episodes,
    page = page
)
