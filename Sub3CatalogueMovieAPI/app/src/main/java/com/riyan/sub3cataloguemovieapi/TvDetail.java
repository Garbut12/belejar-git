package com.riyan.sub3cataloguemovieapi;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class TvDetail extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    TextView TvName, TvDetail;
    ImageView ImgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TvName = findViewById(R.id.tv_judul);
        TvDetail = findViewById(R.id.tv_detail);
        ImgPhoto = findViewById(R.id.img_photo);

        TvItem tvItem = getIntent().getParcelableExtra(EXTRA_TV);

        TvName.setText(tvItem.getNama());
        TvDetail.setText(tvItem.getDetail());

        String imgUrl ="https://image.tmdb.org/t/p/w500/";
        Glide.with(this)
                .load(imgUrl + tvItem.getPhoto())
                .transform(new RoundedCorners(45))
                .into(ImgPhoto);
    }

    public boolean onOptionsItemSelected(MenuItem menu) {
        onBackPressed();
        return true;
    }
}
