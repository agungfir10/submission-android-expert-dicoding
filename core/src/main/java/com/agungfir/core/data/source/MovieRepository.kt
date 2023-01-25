package com.agungfir.core.data.source

import com.agungfir.core.data.NetworkBoundResource
import com.agungfir.core.data.Resource
import com.agungfir.core.data.source.local.LocalDataSource
import com.agungfir.core.data.source.local.entity.MovieEntity
import com.agungfir.core.data.source.remote.RemoteDataSource
import com.agungfir.core.data.source.remote.network.ApiResponse
import com.agungfir.core.data.source.remote.response.MovieResponse
import com.agungfir.core.domain.model.Movie
import com.agungfir.core.domain.repository.IMovieRepository
import com.agungfir.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,

    ) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToMovieEntities(data)
                localDataSource.insertMovies(movieList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map { listMovies ->
            listMovies.map {
                Movie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    mediaType = it.mediaType,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    isFavorite = it.isFavorite
                )
            }
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        Executors.newFixedThreadPool(3).execute {
            val movieEntity = MovieEntity(
                id = movie.id,
                adult = movie.adult,
                backdropPath = movie.backdropPath,
                mediaType = movie.mediaType,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                video = movie.video,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
            )
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}