import * as api from "./utils";

export async function getProducts(payload) {
  try {
    const response = await api.post("/products/all-products", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function getFarmerProducts(payload) {
    try {
      const response = await api.post("/products/farmer-products", payload);
      return response;
    } catch (error) {
      throw error;
    }
  }
