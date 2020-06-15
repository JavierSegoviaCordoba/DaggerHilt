package com.javiersc.daggerHilt.presentation.features.champions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.javiersc.daggerHilt.domain.models.Champion
import com.javiersc.daggerHilt.presentation.features.champions.databinding.ChampionItemBinding
import com.javiersc.daggerHilt.presentation.features.champions.databinding.ChampionsFragmentBinding
import com.javiersc.daggerHilt.presentation.navigation.champions.ChampionsNavigation
import com.javiersc.resources.core.delegates.viewBinding.viewBinding
import com.javiersc.resources.core.extensions.base.recyclerView.listAdapter
import com.javiersc.resources.core.extensions.fragment.launch
import com.javiersc.resources.resource.extensions.folder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class ChampionsFragment : Fragment(R.layout.champions_fragment) {

    private val binding by viewBinding(ChampionsFragmentBinding::bind)

    private val viewModel: ChampionsViewModel by viewModels()

    @Inject
    lateinit var championsNavigation: ChampionsNavigation

    private val adapter by listAdapter(ChampionItemBinding::inflate, Champion::id) { champion ->
        textViewChampionName.text = champion.name
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwipeRefreshLayoutChampions()
        setupRecyclerViewChampions()
        populateChampionsResource()
    }

    private fun setupSwipeRefreshLayoutChampions() = binding.swipeRefreshLayoutChampions.setOnRefreshListener {
        viewModel.getChampions()
    }

    private fun setupRecyclerViewChampions() = with(binding) { recyclerViewChampions.adapter = adapter }

    private fun populateChampionsResource() = launch {
        viewModel.champions.collect { championsResource ->
            championsResource.folder {
                loading { onLoading() }
                noLoading { onNoLoading() }
                success { champions -> onSuccess(champions) }
                error { onError() }
            }
        }
    }

    private fun onLoading() = with(binding) { swipeRefreshLayoutChampions.isRefreshing = true }

    private fun onNoLoading() = with(binding) { swipeRefreshLayoutChampions.isRefreshing = false }

    private fun onSuccess(champions: List<Champion>) = adapter.apply {
        submitList(champions)
        onClick { champion -> championsNavigation.toChampionDetail() }
    }

    private fun onError() = Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
}
