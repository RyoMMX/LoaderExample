package com.ryo.muhammad.loader;

import com.google.gson.Gson;
import com.ryo.muhammad.loader.models.Movie;
import com.ryo.muhammad.loader.models.Root;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static List<Movie> paresJson(String json) {
        Root root = new Gson().fromJson(json, Root.class);
        List<Movie> movies = null;

        if (root != null) {
            movies = root.getResults();
        }


        return movies;
    }
}
