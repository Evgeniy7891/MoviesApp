package ru.cft.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentDetailsBinding
import ru.cft.movieapp.databinding.FragmentFavoriteBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.ui.favorite.FavoriteViewModel
import ru.cft.movieapp.util.ContentModel

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
        viewModel.getDetails(currentMovie.id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.btnFullContent.setOnClickListener {
            createDialog()
        }
    }

    private fun init() {
        with(binding) {
            Glide.with(ivMovie)
                .load(Api.POSTER_DETAILS_URL + currentMovie.backdrop_path)
                .timeout(1000)
                .placeholder(R.drawable.search_holder)
                .error(R.drawable.error_second)
                .into(ivMovie)
            tvTitle.text = currentMovie.title
            tvDescription.text = currentMovie.overview

            ivFavorite.setOnClickListener {
                isFavorite = if (!isFavorite) {
                    ivFavorite.setImageResource(R.drawable.ic_favorite_full)
                    viewModel.insert(currentMovie)
                    true
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite)
                    viewModel.delete(currentMovie)
                    false
                }
            }
        }
    }

    private fun createDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_details, null)
        val myDialog = AlertDialog.Builder(requireContext(), R.style.AlertDialogTheme)
        myDialog.setView(dialogView)
        myDialog.show()
        val image: ImageView = dialogView.findViewById(R.id.iv_poster)
        val title: TextView = dialogView.findViewById(R.id.tv_title)
        val years: TextView = dialogView.findViewById(R.id.tv_years)
        val duration: TextView = dialogView.findViewById(R.id.tv_duration)
        val geners: TextView = dialogView.findViewById(R.id.tv_geners)
        val descriptions: TextView = dialogView.findViewById(R.id.tv_full)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.result.collect { result ->
                if (result != null) {
                    Glide.with(image)
                        .load(Api.POSTER_URL + result.poster_path)
                        .timeout(1000)
                        .placeholder(R.drawable.search_holder)
                        .into(image)

                    title.text = result.title
                    years.text = result.release_date
                    duration.text = result.runtime.toString() + " " + "min."
                    geners.text = result.genres[0].name
                    descriptions.text = result.overview
                }
            }
        }
    }
}
