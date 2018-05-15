import React, { Component } from 'react';
import { Text, View, Image } from 'react-native';

export class MovieTile extends Component {
  render() {
    let movie = this.props.movie;
    if (movie === undefined) return(<View/>);

    return (
      <View style={{borderWidth: 2, flexDirection: 'row', margin: 15}}>
        <Image source={{uri: 'https://image.tmdb.org/t/p/w1280' + movie.poster_path}}
               style={{width: 150, height: 260}} />
        <View style={{justifyContent: 'flex-start', alignItems: 'center', width: 200, margin: 10}}>
          <Text style={{fontSize: 16, fontWeight: 'bold'}}>
            {movie.title}
          </Text>
          <Text style={{fontSize: 10}}>
            {'Rating: ' + movie.vote_average}
          </Text>
          <Text style={{fontSize: 10}}>
            {'Release: ' + movie.release_date}
          </Text>
        </View>
      </View>
    );
  }
}
