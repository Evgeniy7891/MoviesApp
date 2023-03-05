package ru.cft.movieapp.util

import android.content.Context

class LikesHelper {

    companion object {
        fun setFavorite(context: Context, key: String, value: Boolean) {
            val setFavoriteShared = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
            setFavoriteShared.edit().putBoolean(key, value).apply()
        }
        fun getFavorite(context: Context, key: String) : Boolean {
            val getFavoriteShared = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
            return getFavoriteShared.getBoolean(key, false)
        }
    }
}