import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      auth: {
        userRole: currentUser?.role || null
      },
      isDarkMode: false
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        state.auth.userRole = user?.role || null;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        state.auth.userRole = null;
        axios.defaults.headers.common = {};
      },
      TOGGLE_DARK_MODE(state) {
        state.isDarkMode = !state.isDarkMode;
      }
    }
  });
  return store;
}