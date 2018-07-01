package com.ryo.muhammad.loader;

import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String THE_MOVIE_DB_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";

    public static final int THUMBNAIL_IMAGE = 0;
    public static final int LARGE_IMAGE = 1;

    private static final String THE_MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String THE_MOVIE_DB_TOP_RATED_BASE_URL = "https://api.themoviedb.org/3/movie/top_rated";

    private static final String API_KEY_KEY = "api_key";
    private static final String SORT_BY_KEY = "sort_by";
    private static final String PAGE_KEY = "page";

    private static final String TAG = "NetworkUtils";


    public static URL createURL() {
        Uri.Builder builder = Uri.parse(THE_MOVIE_DB_POPULAR_BASE_URL)
                .buildUpon()
                .appendQueryParameter(API_KEY_KEY, "b983f4d87827c8fb33e44a28e9438aa2")
                .appendQueryParameter(PAGE_KEY, String.valueOf(1));

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.e(TAG, url != null ? url.toString() : "null");
        return url;
    }

    public static String getJson(URL url) throws IOException {
        String json = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\A");

        if (scanner.hasNext()) {
            json = scanner.next();
        }

        return json;
    }
}
