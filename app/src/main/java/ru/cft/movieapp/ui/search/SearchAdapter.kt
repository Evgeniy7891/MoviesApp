package ru.cft.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ItemFavoriteMovieBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api

class SearchAdapter (private val listMovies: List<MovieItemModel>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    class ViewHolder(private val binding: ItemFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemModel) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(ivMovie)
                    .load(Api.POSTER_DETAILS_URL + movie.backdrop_path)
                    .placeholder(R.drawable.search_holder)
                    .error(R.drawable.error_second)
                    .timeout(500)
                    .into(ivMovie)
            }
        }
    }
}