import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

var iface = {
  name: 'NativeTextView',
  propTypes: {
    text: PropTypes.string,
    fontSize: PropTypes.integer,
    ...ViewPropTypes, // include the default view properties
  },
};

export const NativeTextView = requireNativeComponent('NativeTextView', iface);