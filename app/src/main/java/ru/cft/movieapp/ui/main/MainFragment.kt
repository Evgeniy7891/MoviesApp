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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.ContentModel
import ru.cft.movieapp.util.MAIN

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentMainBinding
    private var content = mutableListOf<ContentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovies()
        initTv()
    }

    private fun initMovies() {
        viewModel.getInfo()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.result.collect { movies ->
                if (movies != null) {
                    content.add(ContentModel("Popular movies", movies.results))
                }
            }
        }
    }
    private fun initTv() {
        viewModel.getInfoTv()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.resultTv.collect { tv ->
                delay(300)
                if (tv != null) { content.add(ContentModel("Popular TV shows", tv.results)) }
                val adapter =  MainAdapter(content)
                binding.rvListPopular.adapter = adapter

            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun clickMovie(model : MovieItemModel, view: View) {
                val bundle = Bundle()
                bundle.putSerializable("key", model)
                Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_detailsFragment, bundle).onClick(view)
        }
    }

    override fun onStop() {
        content.clear()
        super.onStop()
    }
}