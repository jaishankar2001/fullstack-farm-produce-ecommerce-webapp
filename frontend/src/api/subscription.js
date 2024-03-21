import * as api from "./utils";

export async function placeSubscription(payload) {
    try {
      const response = await api.post("/subscribe/product", payload);
      return response;
    } catch (error) {
      throw error;
    }
  }

  export async function subscriptionHistory() {
    try {
      const response = await api.get("/subscribe/my-subscription");
      return response;
    } catch (error) {
      throw error;
    }
  }
<<<<<<< HEAD

  export async function farmerSubscription() {
    try {
      const response = await api.get("/subscribe/my-subscribed-products");
      return response;
    } catch (error) {
      throw error;
    }
  }
=======
>>>>>>> 492b29209aa02b34f94da04692993e3db77df799
