package com.javiersc.daggerHilt.presentation.navigation.champions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.javiersc.daggerHilt.presentation.navigation.R

internal class ChampionsNavigationImpl(fragment: Fragment) : ChampionsNavigation {

    private val navController = fragment.findNavController()

    override fun toChampionDetail() = navController.navigate(R.id.championDetailFragment)
}
