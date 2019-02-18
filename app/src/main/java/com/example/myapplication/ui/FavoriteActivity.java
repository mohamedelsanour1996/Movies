package com.example.myapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Adapters.MoviesAdapter;
import com.example.myapplication.R;
import com.example.myapplication.mainViewModel;
import com.example.myapplication.model.Movie;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    MoviesAdapter ad;
    private RecyclerView recyclerView;
    private mainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView=findViewById(R.id.listf);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        vm=ViewModelProviders.of(this).get(mainViewModel.class);
        show();


    }

    private void show() {
        vm.getFavoriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    ad=new MoviesAdapter(movies,FavoriteActivity.this);
                    recyclerView.setAdapter(ad);

                      }

            }
        });


    }


}
