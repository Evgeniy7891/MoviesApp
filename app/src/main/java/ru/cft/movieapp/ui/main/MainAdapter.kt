package ru.cft.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.databinding.ItemPopularMovieBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api

class MainAdapter(private val listMovies: List<MovieItemModel>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }


    class ViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemModel) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(ivMovie)
                    .load(Api.POSTER_URL+movie.poster_path)
                    .timeout(300)
                    .into(ivMovie)
            }
        }
    }
}