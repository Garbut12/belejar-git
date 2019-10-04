package com.riyan.sub3cataloguemovieapi;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovie;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        progressBar = view.findViewById(R.id.pb_progress_bar);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, getMovie);

        adapter = new MovieAdapter();
        adapter.notifyDataSetChanged();

        rvMovie = view.findViewById(R.id.rv_list);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);

        movieViewModel.setMovie();
        showLoading(true);
    }

    private Observer<ArrayList<MovieItem>> getMovie = new Observer<ArrayList<MovieItem>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieItem> movieItems) {
            if (movieItems != null) {
                adapter.setData(movieItems);
            }
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
