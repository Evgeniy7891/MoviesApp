package ru.cft.movieapp.ui.details

import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentDetailsBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.util.LikesHelper
import ru.cft.movieapp.util.WAITING_FOR_DATA

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")
    lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
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
        val valueLike = LikesHelper.getFavorite(requireContext(), currentMovie.id.toString())

        if (isFavorite != valueLike) binding.ivFavorite.setImageResource(R.drawable.ic_full_like) else
            binding.ivFavorite.setImageResource(R.drawable.ic_like)

        with(binding) {
            Glide.with(ivMovie)
                .load(Api.POSTER_DETAILS_URL + currentMovie.backdrop_path)
                .timeout(1000)
                .placeholder(R.drawable.search_holder)
                .error(R.drawable.error_second)
                .into(ivMovie)
            tvTitle.text = currentMovie.title

            tvDescription.filters += InputFilter.LengthFilter(200)
            tvDescription.text = currentMovie.overview

            ivFavorite.setOnClickListener {
                isFavorite = if (isFavorite == valueLike) {
                    ivFavorite.setImageResource(R.drawable.ic_full_like)
                    LikesHelper.setFavorite(requireContext(), currentMovie.id.toString(), true)
                    viewModel.insert(currentMovie)
                    true
                } else {
                    ivFavorite.setImageResource(R.drawable.ic_like)
                    LikesHelper.setFavorite(requireContext(), currentMovie.id.toString(), true)
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
        val vote: TextView = dialogView.findViewById(R.id.tv_vote)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.result.collect { result ->
                delay(500)
                if (result != null) {
                    Glide.with(image)
                        .load(Api.POSTER_URL + result.poster_path)
                        .timeout(WAITING_FOR_DATA)
                        .placeholder(R.drawable.tools_poster)
                        .into(image)
                    title.text = result.title
                    years.text = String.format("%s%s", "Realease date ", result.release_date)
                    duration.text =
                        String.format("%s%s", "Duration ", result.runtime.toString(), " min.")
                    geners.text = String.format("%s%s", "Genre ", result.genres[0].name)
                    descriptions.text = result.overview
                    vote.text = String.format("%s%s", "Vote ", result.vote_average.toString())
                } else geners.text = StringBuilder().append("Information in development")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
