package com.javiersc.daggerHilt.presentation.navigation.champions

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
internal object ChampionsNavigationModule {

    @Provides
    fun providesChampionNavigation(fragment: Fragment): ChampionsNavigation = ChampionsNavigationImpl(fragment)
}
