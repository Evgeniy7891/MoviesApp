package ru.cft.movieapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.databinding.ItemFavoriteMovieBinding
import ru.cft.movieapp.databinding.ItemOriginalFavoriteBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api



class FavoriteAdapter(private val listMovies: List<MovieItemModel>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOriginalFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return listMovies.size
    }
    class ViewHolder(private val binding: ItemOriginalFavoriteBinding) :
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
    override fun onViewAttachedToWindow(holder: FavoriteAdapter.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener { view ->
            FavoriteFragment.clickMovie(listMovies[holder.adapterPosition], view)
        }
    }
    override fun onViewDetachedFromWindow(holder: FavoriteAdapter.ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}