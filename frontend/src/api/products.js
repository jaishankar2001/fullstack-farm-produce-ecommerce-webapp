import * as api from "./utils";

export async function getProducts(searchTerm) {
  try {
    const queryParams = searchTerm ? `?searchTerm=${searchTerm}` : '';
    const response = await api.get(`/products/all-products${queryParams}`);
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
