package ru.cft.movieapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesModel(
    val page: Int,
    val results: List<MovieItemModel>,
    val total_pages: Int,
    val total_results: Int
): Parcelable