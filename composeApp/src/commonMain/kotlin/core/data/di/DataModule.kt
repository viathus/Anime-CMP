package core.data.di


import core.data.repository.AnimeRepository
import core.data.repository.AnimeRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AnimeRepositoryImpl).bind<AnimeRepository>()
}