package ru.cft.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.cft.movieapp.databinding.ItemPopularMovieBinding
import ru.cft.movieapp.models.MovieItemModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private var listMovies = emptyList<MovieItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class ViewHolder(private val binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie : MovieItemModel) {
            with(binding) {

            }
        }
    }
}