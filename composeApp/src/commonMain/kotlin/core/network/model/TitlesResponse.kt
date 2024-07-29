package core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitlesResponse(
    @SerialName("en_jp")
    val enJpResponse: String? = null,
    @SerialName("ja_jp")
    val jaJpResponse: String? = null,
    @SerialName("en")
    val enResponse: String? = null
)