package core.data.repository

import core.data.mapper.asEntity
import core.data.mapper.asExternalModel
import core.database.dao.AnimeDao
import core.database.model.AnimeEntity
import core.model.Anime
import core.network.service.AnimeNetworkService

internal class AnimeRepositoryImpl(
    private val animeDao: AnimeDao,
    private val animeNetworkService: AnimeNetworkService
) : AnimeRepository {
    override suspend fun getAllAnime(page: Int): List<Anime> {
        val result = animeDao.getAnimeAllPage().map(AnimeEntity::asExternalModel)
        return cacheOrNetwork(
            result,
            page,
            result.none { it.page == page }
        )
    }

    override suspend fun getAnimeById(id: String): Anime {
        val result = animeDao.getAnimeById(id)
        return result.asExternalModel()
    }

    override suspend fun getAnimeByPage(page: Int): List<Anime> {
        val result = animeDao.getAnimeByPage(page).map(AnimeEntity::asExternalModel)
        return cacheOrNetwork(
            result,
            page,
            result.isEmpty()
        )
    }

    private suspend fun cacheOrNetwork(
        anime: List<Anime>,
        page: Int,
        condition: Boolean
    ): List<Anime> {
        var result = anime
        if (condition) {
            val response = animeNetworkService.getAnime(page).dataResponse.map { it.asEntity(page) }
            animeDao.insertAnime(response)
            result = response.map(AnimeEntity::asExternalModel)
        }

        return result
    }
}