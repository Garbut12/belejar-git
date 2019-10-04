package com.riyan.sub3cataloguemovieapi;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
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
public class TvShow extends Fragment {
    private RecyclerView recyclerView;
    private TvAdapter tvAdapter;
    private ProgressBar progressBar;
    private TvViewModel tvViewModel;


    public TvShow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_progress_bar);

        tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        tvViewModel.getTv().observe(this, getTv);

        tvAdapter = new TvAdapter();
        tvAdapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(tvAdapter);

        tvViewModel.setMovie();
        showLoading(true);
    }

    private Observer<ArrayList<TvItem>> getTv = new Observer<ArrayList<TvItem>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvItem> tvItems) {
            if (tvItems != null) {
                tvAdapter.setData(tvItems);
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
