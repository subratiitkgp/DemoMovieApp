package com.mobileapp;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextView;

public class ReactTextManager extends SimpleViewManager<ReactTextView> {

  public static final String REACT_CLASS = "NativeTextView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public ReactTextView createViewInstance(ThemedReactContext context) {
    ReactTextView textView = new ReactTextView(context);
    return textView;
  }

  @ReactProp(name = "text")
  public void setText(ReactTextView view, String text) {
    view.setText(text);
  }

  @ReactProp(name = "fontSize")
  public void setFontSize(ReactTextView view, int fontSize) {
    view.setTextSize(fontSize);
  }
}