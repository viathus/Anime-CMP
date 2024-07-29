package core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TinyResponse(
    @SerialName("height")
    val heightResponse: Int,
    @SerialName("width")
    val widthResponse: Int
)