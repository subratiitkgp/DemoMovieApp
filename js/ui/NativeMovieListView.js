import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

var iface = {
  name: 'NativeMovieListView',
  propTypes: {
    ...ViewPropTypes, // include the default view properties
  },
};

export const NativeMovieListView = requireNativeComponent('NativeMovieListView', iface);