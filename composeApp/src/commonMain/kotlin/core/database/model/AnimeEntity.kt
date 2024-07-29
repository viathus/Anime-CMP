package core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import core.model.Anime

@Entity(
    tableName = "anime"
)
data class AnimeEntity(
    @PrimaryKey
    val id: String,
    val animeName: String,
    val posterImage: String,
    val description: String,
    val coverImage: String?,
    val youtubeLink: String?,
    val rating: String,
    val episodes: Int?,
    val page: Int,
)
