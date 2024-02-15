import * as api from "./utils";

export async function addFarm(payload) {
  try {
    const response = await api.post("/api/farmer/addfarm", payload);
    return response;
  } catch (error) {
    throw error;
  }
}