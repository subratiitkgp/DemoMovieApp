import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, Button } from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

export class WelcomePage extends Component {
  static navigationOptions = { title: "Welcome" };

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>

        <View style={{margin: 5}}>
          <Button title='Native' onPress={() => this.props.navigation.navigate('MovieListPageNative')} />
        </View>

        <View style={{margin: 5}}>
          <Button title='React Native' onPress={() => this.props.navigation.navigate('MovieListPageRN')} />
        </View>

        <View style={{margin: 5}}>
          <Button title='Web View' onPress={() => this.props.navigation.navigate('MovieListPageMSite')} />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
