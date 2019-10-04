package com.riyan.sub3cataloguemovieapi;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TvViewModel extends ViewModel {
    private static final  String API_KEY ="0859a49de724c804e92e372a662da46f";
    private MutableLiveData<ArrayList<TvItem>> listMutableLiveData = new MutableLiveData<>();

    public void setMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvItem> listtvItems = new ArrayList<>();
        String Url ="https://api.themoviedb.org/3/discover/tv?api_key="+API_KEY+"&language=en-US";
        client.get(Url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responjs = new JSONObject(result);
                    JSONArray lis = responjs.getJSONArray("results");
                    for (int i = 0; i < lis.length(); i++) {
                        JSONObject movie = lis.getJSONObject(i);
                        TvItem tvItem = new TvItem(movie);
                        listtvItems.add(tvItem);
                    }
                    listMutableLiveData.postValue(listtvItems);
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

    LiveData<ArrayList<TvItem>> getTv() {
        return listMutableLiveData;
    }
}
