package com.example.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.myapplication.data.MoviesDatabase;
import com.example.myapplication.model.Movie;

import java.util.List;

public class mainViewModel extends AndroidViewModel {

    MoviesDatabase db;
    private LiveData<List<Movie>> favoriteMovies;

    public mainViewModel(@NonNull Application application) {
        super(application);
        db = MoviesDatabase.getInstance(getApplication());

    }


    public LiveData<List<Movie>> getFavoriteMovies() {
        if (favoriteMovies == null) {
            favoriteMovies = new MutableLiveData<>();
            getFavoritesFromDatabase();
        }
        return favoriteMovies;
    }


    private void getFavoritesFromDatabase() {
        favoriteMovies = db.movieDao().getAllMovies();
    }
}
