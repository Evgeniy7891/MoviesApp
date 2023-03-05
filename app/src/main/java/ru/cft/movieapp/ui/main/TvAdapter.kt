package ru.cft.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ItemContentBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api

class TvAdapter (private val listMovies: List<MovieItemModel>) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return listMovies.size
    }
    class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemModel) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(ivMovie)
                    .load(Api.POSTER_URL+movie.poster_path)
                    .timeout(1000)
                    .placeholder(R.drawable.holder)
                    .error(R.drawable.not_found)
                    .into(ivMovie)
            }
        }
    }
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener { view ->
            MainFragment.clickMovie(listMovies[holder.adapterPosition], view)
        }
    }
    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}