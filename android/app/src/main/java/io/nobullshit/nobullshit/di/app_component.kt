package io.nobullshit.nobullshit.di

import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * App Components
 */
val appComponents: List<Module> = listOf(remoteDataSourceModule)
