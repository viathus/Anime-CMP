package core.network.service

import core.network.model.AnimeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class AnimeNetworkServiceImpl(
    private val httpClient: HttpClient
) : AnimeNetworkService {
    override suspend fun getAnime(page: Int): AnimeResponse {
        val offset = (page - 1) * LIMIT

        val response: AnimeResponse = httpClient.get(
            urlString = "anime?page[limit]=$LIMIT&page[offset]=$offset&sort=popularityRank"
        ).body()

        return response
    }

    companion object {
        const val LIMIT = 20
    }
}