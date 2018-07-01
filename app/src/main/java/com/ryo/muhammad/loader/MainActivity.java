package com.ryo.muhammad.loader;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ryo.muhammad.loader.models.Movie;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Movie>> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderManager loaderManager = getSupportLoaderManager();
        Loader loader = loaderManager.getLoader(0);
        if (loader == null) {
            loaderManager.initLoader(0, null, this).forceLoad();
        } else {
            loader.reset();
            loader.forceLoad();
        }

        RecyclerView recyclerView = findViewById(R.id.movie_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(new ArrayList<Movie>());
        recyclerView.setAdapter(movieAdapter);

    }

    @NonNull
    @Override
    public Loader<List<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        return new MovieAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Movie>> loader, List<Movie> data) {
        movieAdapter.addMovies(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Movie>> loader) {

    }

    private static class MovieAsyncTaskLoader extends AsyncTaskLoader<List<Movie>> {

        public MovieAsyncTaskLoader(@NonNull Context context) {
            super(context);
        }


        @Nullable
        @Override
        public List<Movie> loadInBackground() {
            List<Movie> movies = null;
            URL url = NetworkUtils.createURL();
            try {
                String json = NetworkUtils.getJson(url);
                Log.v(TAG, json);
                movies = JsonUtil.paresJson(json);
                Log.v(TAG, movies.toString());
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "no connection", Toast.LENGTH_SHORT).show();
            }
            return movies;
        }
    }
}
