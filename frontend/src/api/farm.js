import * as api from "./utils";

export async function addFarm(payload) {
  try {
    const response = await api.post("/farmer/addfarm", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function getFarmerFarms(searchTerm) {
  try {
    const queryParams = searchTerm ? `?searchTerm=${encodeURIComponent(searchTerm)}` : '';
    const response = await api.get(`/farmer/own-farms${queryParams}`);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function deleteFarm(id) {
  try {
    const response = await api.del("/farmer/farms/" + id);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function getFarmById(id) {
  try {
    const response = await api.get(`/farmer/getFarm/${id}`);
    return response;
  } catch (error) {
    throw error;
  }
}
