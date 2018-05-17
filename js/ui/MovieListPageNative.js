import React, { Component } from 'react';
import { View, Button } from 'react-native';
import { NativeMovieListView } from './NativeMovieListView';

export class MovieListPageNative extends Component {
  static navigationOptions = { title: "Movie List Native" };

  render() {
    return (
      <View style={{borderWidth: 5, flex: 1, alignItems: 'center', width: "100%", height: "100%"}}>
        <NativeMovieListView style={{width: "100%", height: "100%"}} />
      </View>
    )
  }
}
