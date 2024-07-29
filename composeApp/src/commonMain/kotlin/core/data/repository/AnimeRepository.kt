package core.data.repository

import core.model.Anime

interface AnimeRepository {
    suspend fun getAllAnime(page: Int): List<Anime>
    suspend fun getAnimeById(id: String): Anime
    suspend fun getAnimeByPage(page: Int): List<Anime>
}
