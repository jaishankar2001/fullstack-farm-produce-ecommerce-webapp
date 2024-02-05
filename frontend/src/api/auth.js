import * as api from './utils';

export async function login(payload) {
  try {
    const response = await api.post('/api/auth/signin', payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function register(payload) {
  try {
    const response = await api.post('/api/auth/signup', payload);
    return response;
  } catch (error) {
    throw error;
  }
}