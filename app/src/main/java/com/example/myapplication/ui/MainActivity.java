package com.example.myapplication.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapters.MoviesAdapter;
import com.example.myapplication.R;
import com.example.myapplication.api.MoviesApi;
import com.example.myapplication.data.MoviesDatabase;
import com.example.myapplication.mainViewModel;
import com.example.myapplication.model.Movie;
import com.example.myapplication.model.MoviesList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MoviesAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        getmovie("popular");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.favorite:
                getmovie("Fav");
                break;
            case R.id.popular:
                getmovie("popular");
                break;
            case R.id.toprated:
                getmovie("toprated");
                break;
        }
        return true;
    }

    private void getmovie(String category) {
       Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        MoviesApi apii=retrofit.create(MoviesApi.class);


       if (category.equalsIgnoreCase("popular")) {
           Call<MoviesList> moviesList=apii.getPopularMovies("a1885ca0edcb662258c138683020e889");
           moviesList.enqueue(new Callback<MoviesList>() {
               @Override
               public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                  ad=new MoviesAdapter(response.body().getResults(),MainActivity.this);
                   recyclerView.setAdapter(ad);

               }

               @Override
               public void onFailure(Call<MoviesList> call, Throwable t) {

               }
           });

       }
       else if (category.equalsIgnoreCase("toprated")) {
           Call<MoviesList> moviesList=apii.getTopRatedMovies("a1885ca0edcb662258c138683020e889");
           moviesList.enqueue(new Callback<MoviesList>() {
               @Override
               public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                   ad=new MoviesAdapter(response.body().getResults(),MainActivity.this);
                   recyclerView.setAdapter(ad);

               }

               @Override
               public void onFailure(Call<MoviesList> call, Throwable t) {

               }
           });

       }
       else {
           Intent intent=new Intent(this,FavoriteActivity.class);
           startActivity(intent);

       }







    }
}
