package com.example.myapplication.api;



import com.example.myapplication.model.MoviesList;
import com.example.myapplication.model.ReviewsList;
import com.example.myapplication.model.TrailersList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MoviesApi {
    @GET("movie/top_rated")
    Call<MoviesList> getTopRatedMovies(@Query("api_key")String apiKey);

    @GET("movie/popular")
    Call<MoviesList> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailersList> getTrailersMovies( @Path("id")String id,@Query("api_key")String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewsList> getReviewsMovies(@Path("id")String id,@Query("api_key")String apiKey);

}