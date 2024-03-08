import * as api from "./utils";

export async function getHomeMeta(payload) {
  try {
    const response = await api.get("/home/", payload);
    return response;
  } catch (error) {
    throw error;
  }
}
