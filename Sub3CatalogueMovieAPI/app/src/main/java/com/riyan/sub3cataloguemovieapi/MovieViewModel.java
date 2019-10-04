package com.riyan.sub3cataloguemovieapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "0859a49de724c804e92e372a662da46f";
    private MutableLiveData<ArrayList<MovieItem>> listmovie = new MutableLiveData<>();


    void setMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItem> listitem = new ArrayList<>();
        String url ="https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US" ;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject resJsonObject = new JSONObject(result);
                    JSONArray list = resJsonObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MovieItem movieItem = new MovieItem(movie);
                        listitem.add(movieItem);
                    }
                    listmovie.postValue(listitem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());

            }
        });
    }
    LiveData<ArrayList<MovieItem>> getMovie() {
        return listmovie;
    }
}
