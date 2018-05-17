package com.mobileapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

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

    arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.movie_tile, R.id.textView1, new ArrayList<Movie>());
    listView = (ListView)findViewById(R.id.listView_movie);
    listView.setAdapter(arrayAdapter);
    arrayAdapter.notifyDataSetChanged();

    fetchMovies(1);
  }

  private void renderMovies(MovieResult movieResult, int pageNo) {
    totalPages = movieResult.totalPages;
    currentPage = pageNo;

    arrayAdapter.clear();
    arrayAdapter.addAll(movieResult.results);

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
            System.out.println(response.toString());
            MovieResult movieResult = new Gson().fromJson(response.toString(), MovieResult.class);
            System.out.println(new Gson().toJson(movieResult));
            renderMovies(movieResult, pageNo);
          }
        }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {}
        });

    queue.add(jsonObjectRequest);
  }

  /*
  private static class MyCustomAdapter extends ArrayAdapter<Movie> {
    public MyCustomAdapter(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
      super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Movie movie = getItem(position);

    }
  }
  */

  private static class MovieResult {
    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("results")
    public List<Movie> results;
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
