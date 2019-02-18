package com.example.myapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.ReviewsAdapter;
import com.example.myapplication.Adapters.VideosAdapter;
import com.example.myapplication.MyExecutor;
import com.example.myapplication.R;
import com.example.myapplication.api.MoviesApi;
import com.example.myapplication.data.MoviesDatabase;
import com.example.myapplication.mainViewModel;
import com.example.myapplication.model.Movie;
import com.example.myapplication.model.ReviewsList;
import com.example.myapplication.model.TrailersList;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActvity extends AppCompatActivity {
    private MoviesDatabase mDatabase;
    private Executor executor;
    mainViewModel mv;
    boolean clicked;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mDatabase = MoviesDatabase.getInstance(this);
        executor = new MyExecutor();
        ImageView img=findViewById(R.id.backdrop);
        mv= ViewModelProviders.of(this).get(mainViewModel.class);
        Intent j=getIntent();
        final Movie ob=j.getParcelableExtra("movie");
        Toolbar tb=findViewById(R.id.toolbar);
        TextView title=findViewById(R.id.movieTitleTv);
        TextView relaseDate=findViewById(R.id.releaseDateTv);
        final FloatingActionButton fab=findViewById(R.id.favorite_button);
        TextView rating=findViewById(R.id.userRatingTv);
        TextView plotSynopis=findViewById(R.id.plotSynopsisTv);
        title.setText(ob.getTitle());
        relaseDate.setText(ob.getReleaseDate());
        rating.setText(ob.getVoteAverage().toString());
        plotSynopis.setText(ob.getOverview());
        tb.setTitle(ob.getTitle());
        ImageView iv=findViewById(R.id.poster);
        setSupportActionBar(tb);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mv.getFavoriteMovies().observe(DetailActvity.this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                Log.i("movies", "onChanged: "+movies);
                for (int i=0;i<movies.size();i++){
                    if (movies.get(i).getId().equals(ob.getId())){
                        fab.setImageResource(R.drawable.ic_star_white_24px);
                        clicked=true;
                    }
                }


            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clicked==true){
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            mDatabase.movieDao().deleteMovie(ob);
                        }
                    });
                    Snackbar.make(v, R.string.remove_favorite, Snackbar.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.ic_star_border_white_24px);
                    clicked=false;
                }
                else {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            mDatabase.movieDao().insertMovie(ob);
                        }
                    });
                    Snackbar.make(v, R.string.add_favorite, Snackbar.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.ic_star_white_24px);
                    clicked=true;
                }


            }
        });

        String url = "https://image.tmdb.org/t/p/w500/" + ob.getPosterPath();

        Picasso
                .get()
                .load(url)
                .into(iv);


        url = "https://image.tmdb.org/t/p/w500/" + ob.getBackdropPath();

        Picasso
                .get()
                .load(url)
                .into(img);
        final RecyclerView list=findViewById(R.id.videos_list);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list.setLayoutManager(layoutManager);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        MoviesApi apii=retrofit.create(MoviesApi.class);
        Call<TrailersList> trailerList=apii.getTrailersMovies(ob.getId(),"a1885ca0edcb662258c138683020e889");
        trailerList.enqueue(new Callback<TrailersList>() {
            @Override
            public void onResponse(Call<TrailersList> call, Response<TrailersList> response) {
                VideosAdapter adapter=new VideosAdapter(getApplicationContext(),response.body().getResults());
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<TrailersList> call, Throwable t) {

            }
        });
        final RecyclerView review_list=findViewById(R.id.reviews_list);
        LinearLayoutManager layoutManagerr =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        review_list.setLayoutManager(layoutManagerr);
        Call<ReviewsList>reviewsList=apii.getReviewsMovies(ob.getId(),"a1885ca0edcb662258c138683020e889");
        reviewsList.enqueue(new Callback<ReviewsList>() {
            @Override
            public void onResponse(Call<ReviewsList> call, Response<ReviewsList> response) {
                ReviewsAdapter adapter=new ReviewsAdapter(getApplicationContext(),response.body().getResults());
                review_list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReviewsList> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
