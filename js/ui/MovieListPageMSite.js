import React, { Component } from 'react';
import { View, Button, WebView } from 'react-native';

export class MovieListPageMSite extends Component {
  static navigationOptions = { title: "Movie List MSite" };

  render() {
    return (
      <View style={{borderWidth: 5, flex: 1}}>
        <WebView
          source={{uri: 'https://subratiitkgp.github.io/DemoMovie/'}}
        />
      </View>
    )
  }
}
