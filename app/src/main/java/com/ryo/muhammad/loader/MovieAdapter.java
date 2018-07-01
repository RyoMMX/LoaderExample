package com.ryo.muhammad.loader;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryo.muhammad.loader.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Movie movie = movies.get(position);

        if (movie != null) {
            if (movie.getTitle() != null) {
                holder.titleTV.setText(movie.getTitle());
            }

            if (movie.getPosterPath() != null) {
                Picasso.get().load("http://image.tmdb.org/t/p/w185/" + movie.getPosterPath())
                        .into(holder.posterIV);
            }
        }
    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public final TextView titleTV;
        public final ImageView posterIV;

        public Holder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            posterIV = itemView.findViewById(R.id.poster_im);
        }
    }
}
