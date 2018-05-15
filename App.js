'use strict';

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
import { createStackNavigator } from 'react-navigation';
import { WelcomePage } from './js/ui/WelcomePage';
import { MovieListPageNative } from './js/ui/MovieListPageNative';
import { MovieListPageRN } from './js/ui/MovieListPageRN';
import { MovieListPageMSite } from './js/ui/MovieListPageMSite';

export const App = createStackNavigator({
  WelcomePage: { screen: WelcomePage },
  MovieListPageNative: { screen: MovieListPageNative },
  MovieListPageRN: { screen: MovieListPageRN },
  MovieListPageMSite: { screen: MovieListPageMSite }
});
