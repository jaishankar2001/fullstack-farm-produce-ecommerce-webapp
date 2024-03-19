import * as api from "./utils";

export async function getCategories() {
    try {
      const response = await api.get("/category/list");
      return response;
    } catch (error) {
      throw error;
    }
  }