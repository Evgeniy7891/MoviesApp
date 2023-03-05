package ru.cft.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ItemFavoriteMovieBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.ui.main.TvAdapter
import ru.cft.movieapp.util.ContentModel

class SearchAdapter (private val listMovies: List<ContentModel>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.textHeader.text = item.title
        val list = item.info
        holder.recyclerView.apply {
            adapter = ChildSearchAdapter(list)
        }
    }
    override fun getItemCount(): Int {
        return listMovies.size
    }

    class ViewHolder(private val binding: ItemFavoriteMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val textHeader : TextView = binding.tvNameContent
        val recyclerView: RecyclerView = binding.rvChild
    }
}