import * as api from "./utils";

export async function placeOrder(payload) {
  try {
    const response = await api.post("/order/place-order", payload);
    return response;
  } catch (error) {
    throw error;
  }
}

export async function orderHistory() {
  try {
    const response = await api.get("/order/orderHistory");
    return response;
  } catch (error) {
    throw error;
  }
}
