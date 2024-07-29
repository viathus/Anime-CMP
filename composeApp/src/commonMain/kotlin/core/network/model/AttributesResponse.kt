package core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributesResponse(
    @SerialName("abbreviatedTitles")
    val abbreviatedTitlesResponse: List<String>,
    @SerialName("ageRating")
    val ageRatingResponse: String?,
    @SerialName("ageRatingGuide")
    val ageRatingGuideResponse: String?,
    @SerialName("averageRating")
    val averageRatingResponse: String?,
    @SerialName("canonicalTitle")
    val canonicalTitleResponse: String?,
    @SerialName("coverImage")
    val coverImageResponse: CoverImageResponse?,
    @SerialName("coverImageTopOffset")
    val coverImageTopOffsetResponse: Int?,
    @SerialName("createdAt")
    val createdAtResponse: String?,
    @SerialName("description")
    val descriptionResponse: String,
    @SerialName("endDate")
    val endDateResponse: String?,
    @SerialName("episodeCount")
    val episodeCountResponse: Int?,
    @SerialName("favoritesCount")
    val favoritesCountResponse: Int,
    @SerialName("nsfw")
    val nsfwResponse: Boolean,
    @SerialName("popularityRank")
    val popularityRankResponse: Int,
    @SerialName("posterImage")
    val posterImageResponse: PosterImageResponse,
    @SerialName("ratingRank")
    val ratingRankResponse: Int?,
    @SerialName("showType")
    val showTypeResponse: String?,
    @SerialName("slug")
    val slugResponse: String?,
    @SerialName("startDate")
    val startDateResponse: String? = null,
    @SerialName("status")
    val statusResponse: String,
    @SerialName("subtype")
    val subtypeResponse: String,
    @SerialName("synopsis")
    val synopsisResponse: String,
    @SerialName("titles")
    val titlesResponse: TitlesResponse?,
    @SerialName("totalLength")
    val totalLengthResponse: Int?,
    @SerialName("updatedAt")
    val updatedAtResponse: String,
    @SerialName("userCount")
    val userCountResponse: Int,
    @SerialName("youtubeVideoId")
    val youtubeVideoIdResponse: String?
)