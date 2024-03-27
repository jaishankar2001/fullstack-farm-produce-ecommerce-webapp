import * as api from "./utils";

export async function login(payload) {
  try {
    const response = await api.post("/auth/signin", payload);
    return response;
  } catch (error) {
    console.error("eee", error);
    throw error;
  }
}

export async function register(payload) {
  try {
    const response = await api.post("/auth/signup", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function ResetPasswordReq(payload) {
  try {
    const response = await api.post("/auth/ResetPasswordReq", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function resetPassword(email, code, password) {
  try {
    const apiUrl = `/auth/verify?email=${email}&code=${code}&newPassword=${password}&type=ResetPassword`;
    const response = await api.get(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function verifyEmail(email, code, type) {
  try {
    const apiUrl = `/auth/verify?email=${email}&code=${code}&type=${type}`;
    const response = await api.get(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function walletInit(amount) {
  try {
    const apiUrl = `/wallet/create-payment-intent?amount=${amount}`;
    const response = await api.post(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}
