package ru.cft.movieapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.ItemChildSearchBinding
import ru.cft.movieapp.databinding.ItemContentBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.providers.Api
import ru.cft.movieapp.ui.main.MainFragment
import ru.cft.movieapp.ui.main.TvAdapter

class ChildSearchAdapter (private val listMovies: List<MovieItemModel>) :
    RecyclerView.Adapter<ChildSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemChildSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
        return listMovies.size
    }
    class ViewHolder(private val binding: ItemChildSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieItemModel) {
            with(binding) {
                tvTitle.text = movie.title
                Glide.with(ivMovie)
                    .load(Api.POSTER_DETAILS_URL + movie.backdrop_path)
                    .placeholder(R.drawable.search_holder)
                    .error(R.drawable.error_second)
                    .timeout(1500)
                    .into(ivMovie)
            }
        }
    }
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener { view ->
            SearchFragment.clickMovie(listMovies[holder.adapterPosition], view)
        }
    }
    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}