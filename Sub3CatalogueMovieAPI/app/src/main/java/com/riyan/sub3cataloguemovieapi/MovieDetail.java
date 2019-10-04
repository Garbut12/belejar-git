package com.riyan.sub3cataloguemovieapi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class MovieDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView tvNama, tvDetail;
    ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvNama = findViewById(R.id.tv_judul);
        tvDetail = findViewById(R.id.tv_detail);
        imgPhoto = findViewById(R.id.img_photo);

        MovieItem movieItem = getIntent().getParcelableExtra(EXTRA_MOVIE);

        tvNama.setText(movieItem.getNama());
        tvDetail.setText(movieItem.getDetail());

        String imageUrl ="https://image.tmdb.org/t/p/w500/";
        Glide.with(this)
                .load(imageUrl + movieItem.getFoto())
                .transform(new RoundedCorners(45))
                .into(imgPhoto);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
