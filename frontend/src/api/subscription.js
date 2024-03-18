import * as api from "./utils";

export async function placeSubscription(payload) {
    try {
      const response = await api.post("/subscribe/product", payload);
      return response;
    } catch (error) {
      throw error;
    }
  }
