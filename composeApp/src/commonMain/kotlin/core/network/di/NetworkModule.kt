package core.network.di

import core.network.service.AnimeNetworkService
import core.network.service.AnimeNetworkServiceImpl
import core.network.service.client
import io.ktor.client.HttpClient
import org.koin.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::client).bind<HttpClient>()
    singleOf(::AnimeNetworkServiceImpl).bind<AnimeNetworkService>()
}
