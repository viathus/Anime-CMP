package app

import core.common.di.commonModule
import core.data.di.dataModule
import core.database.di.databaseModule
import core.domain.di.domainModule
import core.network.di.networkModule
import features.animedetails.AnimeDetailViewModel
import features.home.HomeViewModel
import features.seemore.SeeMoreViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val platformModule: Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            databaseModule,
            dataModule,
            platformModule,
            featureModule,
            commonModule,
            domainModule,
        )
    }
}

val featureModule = module {
    viewModelOf(::SeeMoreViewModel)
    viewModelOf(::AnimeDetailViewModel)
    viewModelOf(::HomeViewModel)
}