package ru.cft.movieapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentFavoriteBinding
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.ui.main.MainAdapter
import ru.cft.movieapp.ui.main.MainViewModel

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun init() {
        viewModel.gelAllMovies()
            viewModel.favoriteMovieList.observe(viewLifecycleOwner) { list ->
            val adapter = FavoriteAdapter(list.asReversed())
            binding.rvListFavorite.adapter = adapter
        }
    }
    companion object {
        fun clickMovie(model : MovieItemModel, view: View) {
            val bundle = Bundle()
            bundle.putSerializable("key", model)
            Navigation.createNavigateOnClickListener(R.id.action_favoriteFragment_to_detailsFragment, bundle).onClick(view)
        }
    }
}