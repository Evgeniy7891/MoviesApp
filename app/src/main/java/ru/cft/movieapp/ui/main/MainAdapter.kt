package ru.cft.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ItemPopularMovieBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.util.ContentModel
import ru.cft.movieapp.util.MAIN

class MainAdapter(private val listMovies: List<ContentModel>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.textPopular.text = item.title
        val list = item.info
        holder.recyclerView.apply {
                adapter = TvAdapter(list)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    class ViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val textPopular: TextView = binding.tvPopular
        val recyclerView: RecyclerView = binding.rvContent
    }
}