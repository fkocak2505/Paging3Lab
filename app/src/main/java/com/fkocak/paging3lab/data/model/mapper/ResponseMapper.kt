package com.fkocak.paging3lab.data.model.mapper

import com.fkocak.paging3lab.data.model.ResponseDto
import com.fkocak.paging3lab.data.model.ResponseItem
import com.fkocak.paging3lab.domain.model.Movie

fun ResponseItem.mapFromEntity() = Movie(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath.orEmpty(),
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity,
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    title = this.title.orEmpty(),
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<ResponseItem>.mapFromListModel(): List<Movie> {
    return this.map {
        it.mapFromEntity()
    }
}
