package ru.cft.movieapp.ui.search

import android.os.Bundle
import android.util.Log
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
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.databinding.FragmentSearchBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.ui.main.MainAdapter
import ru.cft.movieapp.ui.main.MainViewModel
import ru.cft.movieapp.util.ContentModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding
    private var content = mutableListOf<ContentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(content.isEmpty()) {
            val adapter = SearchAdapter(content).notifyDataSetChanged()
        }
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
                    Log.d("TAG", "Movies - $movies")
                    content.add(ContentModel("Movies", movies.results))
                    Log.d("TAG", "Movies2 - $content")
                }
            }
        }
    }
    private fun initTv(movie: String) {
        viewModel.getInfoTv(movie)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.resultTv.collect { tv ->
                delay(300)
                Log.d("TAG", " TV")
                if (tv != null) {
                    Log.d("TAG", " TV - $tv")
                    content.add(ContentModel("TV shows", tv.results))
                    Log.d("TAG", " TV - $content")
                }
            }
        }
    }

    private fun initRecyclerView() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(600)
            Log.d("TAG", " Rec - $content")
            val adapter = SearchAdapter(content)
            binding.rvListSearch.adapter = adapter
            binding.rvListSearch.itemAnimator = null
            binding.btnSearch.isLoading = false
        }
    }

    companion object {
        fun clickMovie(model : MovieItemModel, view: View) {
            val bundle = Bundle()
            bundle.putSerializable("key", model)
            Navigation.createNavigateOnClickListener(R.id.action_searchFragment_to_detailsFragment, bundle).onClick(view)
        }
    }
}
