package ru.cft.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentDetailsBinding
import ru.cft.movieapp.databinding.FragmentFavoriteBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.ui.favorite.FavoriteViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    lateinit var binding: FragmentDetailsBinding
    lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("key") as MovieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        with(binding) {
            Glide.with(ivMovie)
                .load(Api.POSTER_DETAILS_URL+currentMovie.backdrop_path)
                .timeout(500)
                .into(ivMovie)
            tvTitle.text = currentMovie.title
            tvDescription.text = currentMovie.overview

            ivFavorite.setOnClickListener {
                isFavorite = if(!isFavorite) {
                    ivFavorite.setImageResource(R.drawable.ic_favorite_full)
                    true
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite)
                    false
                }
            }
        }
    }
}