package core.database.di

import core.database.AnimeDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { get<AnimeDatabase>().getAnimeDao() }
}