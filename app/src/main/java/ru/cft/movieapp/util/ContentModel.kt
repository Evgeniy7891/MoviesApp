package ru.cft.movieapp.util

import ru.cft.movieapp.models.MovieItemModel

data class ContentModel(
    val title: String ="",
    var info: List<MovieItemModel>
)
