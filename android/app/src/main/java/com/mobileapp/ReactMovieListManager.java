package com.mobileapp;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class ReactMovieListManager extends SimpleViewManager<MovieListView> {

  public static final String REACT_CLASS = "NativeMovieListView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public MovieListView createViewInstance(ThemedReactContext context) {
    return new MovieListView(context);
  }
}