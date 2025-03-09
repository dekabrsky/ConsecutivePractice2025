package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.model

import com.google.gson.annotations.SerializedName

class MoviesSearchResponse(
    @SerializedName("Search")
    val search: List<MovieShortResponse>?
): MovieApiBaseResponse()

class MovieShortResponse(
    @SerializedName("imdbID")
    val id: String?,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Year")
    val year: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Poster")
    val posterUrl: String?,
)