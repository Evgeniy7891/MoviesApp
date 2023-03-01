package ru.cft.movieapp.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.MAIN

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initMovies() {
        viewModel.getInfo()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.result.collect {
                val list = it?.results
                val adapter = list?.let { movies -> MainAdapter(movies) }
                binding.rvListPopular.adapter = adapter

            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_favorite -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        fun clickMovie(model : MovieItemModel, view: View) {
            val bundle = Bundle()
            bundle.putSerializable("key", model)
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_detailsFragment, bundle).onClick(view)
        }
    }
}