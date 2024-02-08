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

export async function ResetPasswordReq(payload) {
  try {
    const response = await api.post('/api/auth/ResetPasswordReq', payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function resetPassword(email, code, password) {
  try {
    const apiUrl = `http://localhost:8080/api/auth/verify?email=${email}&code=${code}&newPassword=${password}&type=ResetPassword`;
    const response = await api.get(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function verifyEmail(email, code, type) {
  try {
      const apiUrl = `http://localhost:8080/api/auth/verify?email=${email}&code=${code}&type=${type}`;
      const response = await api.get(apiUrl);
      return response; // Assuming the response contains the data you need
  } catch (error) {
      throw error;
  }
}
