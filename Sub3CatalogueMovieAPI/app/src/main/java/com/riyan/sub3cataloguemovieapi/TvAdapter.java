package com.riyan.sub3cataloguemovieapi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MovieViewHolder> {
    private ArrayList<TvItem> tvItems = new ArrayList<>();

    public void setData(ArrayList<TvItem> items) {
        tvItems.clear();
        tvItems.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TvAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvAdapter.MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(tvItems.get(i));
        final TvItem tvItem = tvItems.get(i);

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((movieViewHolder.itemView.getContext()), TvDetail.class);

                TvItem tvItem1 = new TvItem();
                tvItem1.setNama(tvItem.getNama());
                tvItem1.setDetail(tvItem.getDetail());
                tvItem1.setPhoto(tvItem.getPhoto());

                intent.putExtra(TvDetail.EXTRA_TV, tvItem1);
                movieViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        String vie = "" + tvItems.size();
        Log.d("tvItems", vie);
        return tvItems.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDetail;
        ImageView ImgPhoto;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            ImgPhoto = itemView.findViewById(R.id.img_photo);
        }

        public void bind(TvItem tvItem) {
            String imgUrl ="https://image.tmdb.org/t/p/w780/";
            tvJudul.setText(tvItem.getNama());

            Glide.with(itemView.getContext())
                    .load(imgUrl + tvItem.getPhoto())
                    .transform(new RoundedCorners(45))
                    .into(ImgPhoto);
        }
    }
}
