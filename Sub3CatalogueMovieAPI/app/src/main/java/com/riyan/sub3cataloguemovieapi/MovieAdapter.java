package com.riyan.sub3cataloguemovieapi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieItem> movieItem = new ArrayList<>();

    public void setData(ArrayList<MovieItem> items) {
        movieItem.clear();
        movieItem.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(movieItem.get(i));
        final MovieItem movieItems = movieItem.get(i);

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(movieViewHolder.itemView.getContext(), MovieDetail.class);
                MovieItem item = new MovieItem();
                item.setNama(movieItems.getNama());
                item.setDetail(movieItems.getDetail());
                item.setFoto(movieItems.getFoto());

                intent.putExtra(MovieDetail.EXTRA_MOVIE, item);
                movieViewHolder.itemView.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return movieItem.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView TvJudul;
        ImageView ImgPhoto;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            TvJudul = itemView.findViewById(R.id.tv_judul);
            ImgPhoto = itemView.findViewById(R.id.img_photo);
        }

        public void bind(MovieItem movieItem) {
            String imgUrl ="https://image.tmdb.org/t/p/w780/";
            TvJudul.setText(movieItem.getNama());

            Glide.with(itemView.getContext())
                    .load(imgUrl + movieItem.getFoto())
                    .transform(new RoundedCorners(45))
                    .into(ImgPhoto);
        }
    }
}
