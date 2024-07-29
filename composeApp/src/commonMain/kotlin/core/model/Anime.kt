package core.model

data class Anime(
    val id: String,
    val animeName: String,
    val posterImage: String,
    val description: String,
    val coverImage: String?,
    val youtubeLink: String?,
    val rating: String,
    val episodes: Int?,
    val page: Int
)
