package com.example.myapplication.Adapters;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MyExecutor;
import com.example.myapplication.data.MoviesDatabase;
import com.example.myapplication.mainViewModel;
import com.example.myapplication.ui.DetailActvity;
import com.example.myapplication.R;
import com.example.myapplication.model.Movie;
import com.example.myapplication.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MoviesAdapter extends RecyclerView.Adapter <MoviesAdapter.info_holder>{

    List<Movie> gg = new ArrayList<>();
    Activity co;
    private Executor executor;
    private MoviesDatabase mDb;
    final List<String>id=new ArrayList<>();

    public MoviesAdapter(List<Movie> gg, Activity activity) {
        this.gg = gg;
        this.co= activity;
        this.mDb=MoviesDatabase.getInstance(activity);
        this.executor = new MyExecutor();
    }

    @NonNull
    @Override
    public info_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(co).inflate(R.layout.movies_list_item,parent,false);
        info_holder holder=new info_holder(row);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final info_holder holder, final int position) {
        final Movie o=gg.get(position);



        holder.title.setText(o.getTitle());

        holder.Rate.setText(o.getVoteAverage().toString());

        holder.Poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(co, DetailActvity.class);
                i.putExtra("movie",  o);
                co.startActivity(i);
            }
        });

        String url = "https://image.tmdb.org/t/p/w500/" + o.getPosterPath();

        Picasso
                .get()
                .load(url)
                .into(holder.Poster);


    }


    @Override
    public int getItemCount() {
        return gg.size();
    }
    class info_holder extends RecyclerView.ViewHolder{
        ImageView Poster;
        TextView title;
        TextView Rate;
        public info_holder(final View itemView) {
            super(itemView);
            Poster=itemView.findViewById(R.id.MovieImage);
            title=itemView.findViewById(R.id.movieTitle);
            Rate=itemView.findViewById(R.id.movieRate);
        }
    }
}

