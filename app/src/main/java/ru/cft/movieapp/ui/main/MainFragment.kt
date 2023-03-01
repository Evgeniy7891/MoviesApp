package ru.cft.movieapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

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
}