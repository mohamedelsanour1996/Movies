package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Review;
import com.example.myapplication.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.Review_holder>{
    Context co;
    List<Review> bb;

    public ReviewsAdapter(Context co, List<Review> bb) {
        this.co = co;
        this.bb = bb;
    }

    @NonNull
    @Override
    public Review_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(co.getApplicationContext()).inflate(R.layout.reviews_list_item, parent, false);
        Review_holder holder=new Review_holder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Review_holder holder, int position) {
        final Review rv=bb.get(position);
        holder.authorTv.setText(rv.getAuthor());
        holder.contentTv.setText(rv.getContent());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            }
        });



    }

    @Override
    public int getItemCount() {
        return bb.size();
    }


    class Review_holder extends RecyclerView.ViewHolder{
        TextView authorTv;
        TextView contentTv;
        CardView cv;
        public Review_holder(final View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.reviewCardView);
            contentTv=itemView.findViewById(R.id.contentTv);
            authorTv=itemView.findViewById(R.id.authorTv);

        }
    }

}
