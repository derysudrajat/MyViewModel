package com.example.myviewmodel;

import android.util.Log;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherItems {
    private int id;
    private String name;
    private String currentWeather;
    private String description;
    private String temperature;

    WeatherItems(JSONObject object) {
        try {
            String mTitle = object.getString("title");
            String mReleaseDate = object.getString("release_date");
            String mOverview = object.getString("overview");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date mDate = formatter.parse(mReleaseDate);
            formatter = new SimpleDateFormat("dd MMM yyyy");
            String strDate = formatter.format(mDate);

            this.name = mTitle;
            this.currentWeather = currentWeather;
            this.description = mOverview;
            this.temperature = strDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
