package ru.cft.movieapp.util

import android.content.Context

object LikesHelper {

    val setFavoriteShared = MAIN.getSharedPreferences("repo", Context.MODE_PRIVATE)

    fun setFavorite(key: String, value: Boolean) {
        setFavoriteShared.edit().putBoolean(key, value).apply()
    }

    fun getFavorite(key: String): Boolean {
        return setFavoriteShared.getBoolean(key, false)
    }
}
