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
import com.example.myapplication.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.List;


public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.video_holder>{
    Context co;
    List<Trailer> bb;

    public VideosAdapter(Context co, List<Trailer> bb) {
        this.co = co;
        this.bb = bb;
    }

    @NonNull
    @Override
    public video_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row=LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item,parent,false);
        video_holder holder=new video_holder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull video_holder holder, int position) {
        final Trailer o=bb.get(position);
        holder.nameTv.setText(o.getName());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +o.getKey()));
                intent.putExtra("VIDEO_ID",o.getKey());
                co.startActivity(intent);

            }
        });

        String photoUrl = String.format("https://img.youtube.com/vi/%s/0.jpg", o.getKey());

        Picasso
                .get()
                .load(photoUrl)
                .into(holder.videoIv);


    }

    @Override
    public int getItemCount() {
        return bb.size();
    }

    class video_holder extends RecyclerView.ViewHolder{
        ImageView videoIv;
        TextView nameTv;
        CardView cv;
        public video_holder(final View itemView) {
            super(itemView);
            cv=itemView.findViewById(R.id.videoCardView);
            videoIv=itemView.findViewById(R.id.videoIv);
            nameTv=itemView.findViewById(R.id.nameTv);

        }
    }

}
