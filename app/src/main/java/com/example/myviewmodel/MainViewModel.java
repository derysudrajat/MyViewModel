package com.example.myviewmodel;

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

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "0a597bad68c0b95d5fab612cff9d8891";
    private MutableLiveData<ArrayList<WeatherItems>> listWeathers = new MutableLiveData<>();
    AsyncHttpClient client = new AsyncHttpClient();
    final ArrayList<WeatherItems> listItems = new ArrayList<>();

    LiveData<ArrayList<WeatherItems>> getWeathers() {
        return listWeathers;
    }

    void setWeather() {
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en#";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        String id = weather.getString("id");
                        Log.d("JSON ","id: "+id);
                        getDetailMovie(id);
                    }
                    listWeathers.postValue(listItems);
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

    void getDetailMovie(String id){
        String url = "https://api.themoviedb.org/3/movie/"+id+"?api_key="+API_KEY+"&language=en#";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    WeatherItems weatherItems = new WeatherItems(responseObject);
                    listItems.add(weatherItems);
                }catch (Exception e){
                    Log.d("JSON Execption: ",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

}