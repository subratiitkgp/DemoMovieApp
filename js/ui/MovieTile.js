import React, { Component } from 'react';
import { View, Button, Image } from 'react-native';

export class MovieTile extends Component() {
  render() {
    let movie = this.props.movie;

    return (
      <View style={{borderWidth: 1, width: '100%', flexDirection: 'row'}}>
        <Image source={{uri: 'https://image.tmdb.org/t/p/w1280' + movie.poster_path}} />
        <View>
          <Text>
            {movie.title}
          </Text>
          <Text>
            {'Rating: ' + movie.vote_average}
          </Text>
          <Text>
            {'Release: ' + movie.release_date}
          </Text>
        </View>
      </View>
    );
  }
}