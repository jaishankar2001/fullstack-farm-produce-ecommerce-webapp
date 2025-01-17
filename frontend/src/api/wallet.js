import * as api from "./utils";

export async function getWalletHistory() {
  try {
    const apiUrl = `/wallet/history`;
    const response = await api.get(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}
