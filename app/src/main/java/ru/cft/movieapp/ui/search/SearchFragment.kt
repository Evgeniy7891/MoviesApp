package ru.cft.movieapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentSearchBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.ContentModel
import ru.cft.movieapp.util.WAITING_FOR_DATA

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")
    private var content = mutableListOf<ContentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchMovies()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun searchMovies() {
        with(binding) {
            btnSearch.setOnClickListener {
                val movie = etSearchMovie.text.toString()
                if (movie != "") {
                    content.clear()
                    initMovies(movie)
                    initTv(movie)
                    btnSearch.isLoading = true
                    initRecyclerView()
                }
            }
        }
    }

    private fun initMovies(movie: String) {
        viewModel.getInfo(movie)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.result.collect { movies ->
                if (movies != null) {
                    content.add(ContentModel("Movies", movies.results))
                }
            }
        }
    }
    private fun initTv(movie: String) {
        viewModel.getInfoTv(movie)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.resultTv.collect { tv ->
                if (tv != null) {
                    content.add(ContentModel("TV shows", tv.results))
                }
            }
        }
    }

    private fun initRecyclerView() {
        CoroutineScope(Dispatchers.Main).launch {
            val adapter = SearchAdapter(content)
            binding.rvListSearch.adapter = adapter
            binding.rvListSearch.itemAnimator = null
            binding.btnSearch.isLoading = false
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun clickMovie(model : MovieItemModel, view: View) {
            val bundle = Bundle()
            bundle.putSerializable("key", model)
            Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_detailsFragment, bundle).onClick(view)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
