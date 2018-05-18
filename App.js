'use strict';

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
import codePush from "react-native-code-push";

import { createStackNavigator } from 'react-navigation';
import { WelcomePage } from './js/ui/WelcomePage';
import { MovieListPageNative } from './js/ui/MovieListPageNative';
import { MovieListPageRN } from './js/ui/MovieListPageRN';
import { MovieListPageMSite } from './js/ui/MovieListPageMSite';

const App = createStackNavigator({
  WelcomePage: { screen: WelcomePage },
  MovieListPageNative: { screen: MovieListPageNative },
  MovieListPageRN: { screen: MovieListPageRN },
  MovieListPageMSite: { screen: MovieListPageMSite }
});

let codePushOptions = { checkFrequency: codePush.CheckFrequency.ON_APP_RESUME };
App = codePush(codePushOptions)(App);
export {App};