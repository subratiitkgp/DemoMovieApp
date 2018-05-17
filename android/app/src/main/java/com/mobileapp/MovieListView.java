package com.mobileapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieListView extends LinearLayout {
  private int currentPage = 1;
  private int totalPages = 1;
  ArrayAdapter<Movie> arrayAdapter;
  ListView listView;
  ThemedReactContext context;

  public MovieListView(ThemedReactContext context) {
    super(context);
    this.context = context;

    inflate(context, R.layout.movie_list_view, this);

    listView = (ListView) findViewById(R.id.listView_movie);

    final Button prevButton = (Button) findViewById(R.id.button_previous);
    final Button nextButton = (Button) findViewById(R.id.button_next);

    prevButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (currentPage <= 1) {
          currentPage = 1;
          return;
        }

        fetchMovies(--currentPage);
      }
    });

    nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (currentPage >= totalPages) {
          currentPage = totalPages;
          return;
        }

        fetchMovies(++currentPage);
      }
    });

    fetchMovies(1);
  }

  private void renderMovies(MovieResult movieResult, int pageNo) {
    totalPages = movieResult.totalPages;
    currentPage = pageNo;

    if (arrayAdapter == null) {
      arrayAdapter = new CustomAdapter(context, movieResult.results);
      listView.setAdapter(arrayAdapter);
    } else {
      arrayAdapter.clear();
      arrayAdapter.addAll(movieResult.results);
    }

    if (context.getCurrentActivity() != null) {
      context.getCurrentActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
          arrayAdapter.notifyDataSetChanged();
        }
      });
    }
  }

  private void fetchMovies(final int pageNo) {
    if (pageNo > totalPages) return;
    if (pageNo < 1) return;

    RequestQueue queue = Volley.newRequestQueue(getContext());
    final String url = "https://api.themoviedb.org/4/list/1?page=" + pageNo + "&api_key=920491e2b5c27c74377d82829dad86b3";

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
          @Override
          public void onResponse(JSONObject response) {
            MovieResult movieResult = new Gson().fromJson(response.toString(), MovieResult.class);
            renderMovies(movieResult, pageNo);
          }
        }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {}
        });

    queue.add(jsonObjectRequest);
  }

  private static class CustomAdapter extends ArrayAdapter<Movie> {
    public CustomAdapter(Context context, ArrayList<Movie> users) {
      super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // Get the data item for this position
      Movie movie = getItem(position);

      if (movie == null) return convertView;

      // Check if an existing view is being reused, otherwise inflate the view
      if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_tile, parent, false);
      }

      // Lookup view for data population
      ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
      String url = "https://image.tmdb.org/t/p/w1280" + movie.posterPath;
      Picasso.get().load(url).into(imageView);

      TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
      TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
      TextView textView3 = (TextView) convertView.findViewById(R.id.textView3);

      // Populate the data into the template view using the data object
      textView1.setText(movie.title);
      textView2.setText("Rating: " + movie.voteAverage);
      textView3.setText("Release: " + movie.releaseDate);
      
      // Return the completed view to render on screen
      return convertView;
    }
  }

  private static class MovieResult {
    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("results")
    public ArrayList<Movie> results;
  }

  private static class Movie {
    @SerializedName("id")
    public int id;

    @SerializedName("adult")
    public boolean adult;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("title")
    public String title;

    @SerializedName("vote_average")
    public String voteAverage;

    @SerializedName("release_date")
    public String releaseDate;
  }
}
