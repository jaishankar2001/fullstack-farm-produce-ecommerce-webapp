import * as api from "./utils";

export async function login(payload) {
  try {
    const response = await api.post("/api/auth/signin", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function register(payload) {
  try {
    const response = await api.post("/api/auth/signup", payload);
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

export async function walletInit(amount) {
  try {
    const apiUrl = `http://localhost:8080/api/auth/create-payment-intent?amount=${amount}`;
    const response = await api.post(apiUrl);
    return response; // Assuming the response contains the data you need
  } catch (error) {
    throw error;
  }
}
