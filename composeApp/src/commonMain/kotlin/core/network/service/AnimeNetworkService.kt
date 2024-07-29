package core.network.service

import core.network.model.AnimeResponse

internal interface AnimeNetworkService {
    suspend fun getAnime(page: Int): AnimeResponse
}