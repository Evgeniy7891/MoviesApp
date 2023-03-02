package ru.cft.movieapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.databinding.FragmentSearchBinding
import ru.cft.movieapp.ui.main.MainAdapter
import ru.cft.movieapp.ui.main.MainViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun searchMovies() {
        viewModel.getInfo("Moscow")
//        viewModel.initDatabase()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.result.collect {
                val list = it?.results
                val adapter = list?.let { movies -> SearchAdapter(movies) }
                binding.rvListFavorite.adapter = adapter

            }
        }
    }
}