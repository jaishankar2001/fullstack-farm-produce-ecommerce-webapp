import * as api from "./utils";

export async function getProducts(payload) {
  try {
    const response = await api.post("/products/all-products", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function getProductById(id) {
  try {
    const response = await api.get(`/products/getProduct/${id}`);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function deleteProduct(id) {
  try {
    const response = await api.del(`/products/delete/${id}`);
    return response;
  } catch (error) {
    throw error;
  }
}


export async function getFarmerProducts(payload) {
    try {
      const response = await api.post("/products/delete", payload);
      return response;
    } catch (error) {
      throw error;
    }
  }
