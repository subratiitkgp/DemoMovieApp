import React, { Component } from 'react';
import { View, Text, Button, FlatList } from 'react-native';
import { MovieTile } from './MovieTile';

export class MovieListPageRN extends Component {
  constructor (props) {
    super(props);

    this.state = {movies: []};

    this.handlePreviousClick = this.handlePreviousClick.bind(this);
    this.handleNextClick = this.handleNextClick.bind(this);
    this.pageCount = 1;
    this.totalPages = 1;

    this.fetchMovies();
  }

  static navigationOptions = { title: "Movie List RN" };

  renderButtons() {
    return (
      <View style={{borderWidth: 1, flexDirection: 'row', width: '100%', justifyContent: 'center'}}>
        <View style={{margin: 5}}>
          <Button title='Previous' onPress={this.handlePreviousClick} />
        </View>

        <View style={{margin: 5}}>
          <Button title='Next'onPress={this.handleNextClick} />
        </View>
      </View>
    );
  }

  renderMovies() {
    const movies = this.state.movies;
    const moviesFiltered = movies.filter(movie => movie.adult === false);

    return (
      <View style={{borderWidth: 1, width: '100%'}}>
        <FlatList
          removeClippedSubviews={true}
          data={moviesFiltered}
          keyExtractor={(movie) => movie.id.toString()}
          initialNumToRender={1}
          renderItem={(item) => {
            let movie = item.item;

            return (
              <MovieTile key={movie.id} movie={movie} />
            );
          }}
        />
      </View>
    );
  }


  render() {
    return (
      <View style={{borderWidth: 5, alignItems: 'center', flex: 1}}>
        {this.renderButtons()}
        {this.renderMovies()}
      </View>
    );
  }

  nextPage() {
  }

  previousPage() {
  }

  fetchMovies() {
    fetch("https://api.themoviedb.org/4/list/1?page=" + this.pageCount + "&api_key=920491e2b5c27c74377d82829dad86b3")
      .then((response) => response.json())
      .then((responseJson) => {
        this.totalPages = responseJson.total_pages;
        this.setState({movies: responseJson.results});
      });
  }

  handlePreviousClick() {
    if (this.pageCount === 1) {
      return;
    }

    if (this.pageCount < 1) {
      this.pageCount = 1;
      this.fetchMovies();
      return;
    }

    this.pageCount = this.pageCount - 1;
    this.fetchMovies();
  }

  handleNextClick() {
    if (this.pageCount === this.totalPages) {
      return;
    }

    if (this.pageCount > this.totalPages) {
      this.pageCount = this.totalPages;
      this.fetchMovies();
      return;
    }

    this.pageCount = this.pageCount + 1;
    this.fetchMovies();
  }
}