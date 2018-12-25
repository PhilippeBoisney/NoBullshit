package io.nobullshit.nobullshit.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.nobullshit.nobullshit.ui.joblist.JobListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeJobListFragment(): JobListFragment
}