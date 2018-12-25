package io.nobullshit.nobullshit.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.nobullshit.nobullshit.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}