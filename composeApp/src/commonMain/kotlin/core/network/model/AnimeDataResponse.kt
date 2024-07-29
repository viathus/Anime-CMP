package core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDataResponse(
    @SerialName("attributes")
    val attributesResponse: AttributesResponse,
    @SerialName("id")
    val idResponse: String,
    @SerialName("type")
    val typeResponse: String
)