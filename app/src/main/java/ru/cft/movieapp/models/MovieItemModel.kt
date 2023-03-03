package ru.cft.movieapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
data class MovieItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val adult: Boolean,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var backdrop_path: String?,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var original_language: String?,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var original_title: String?,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var overview: String?,
    var popularity: Double,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var poster_path: String?,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var release_date: String?,
    @ColumnInfo(defaultValue = "'CURRENT_TIMESTAMP'")
    var title: String?,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable, java.io.Serializable {

    override fun hashCode(): Int {
       var result = id.hashCode()
        if(title == null || original_title == null) {
            result *= 31
        }
        return result
    }
}