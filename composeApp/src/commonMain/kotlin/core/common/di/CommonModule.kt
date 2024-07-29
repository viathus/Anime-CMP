package core.common.di

import Formatter

import org.koin.dsl.module

val commonModule = module {
    single { Formatter() }
}