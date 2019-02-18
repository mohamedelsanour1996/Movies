package com.example.myapplication.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.myapplication.model.Movie;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM Movie WHERE id = :id")
    LiveData<Movie> getMovieById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);


}
