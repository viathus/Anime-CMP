package core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PosterImageResponse(
    @SerialName("large")
    val largeResponse: String,
    @SerialName("medium")
    val mediumResponse: String,
    @SerialName("original")
    val originalResponse: String,
    @SerialName("small")
    val smallResponse: String,
    @SerialName("tiny")
    val tinyResponse: String
)