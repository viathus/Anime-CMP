package core.domain.di

import core.domain.CalculateRatingUseCase
import core.domain.CalculateRatingUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {
    singleOf(::CalculateRatingUseCaseImpl).bind<CalculateRatingUseCase>()
}