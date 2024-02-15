import * as api from "./utils";

export async function getWalletHistory() {
  try {
    const apiUrl = `http://localhost:8080/api/wallet/history`;
    const response = await api.get(apiUrl);
    return response;
  } catch (error) {
    throw error;
  }
}
