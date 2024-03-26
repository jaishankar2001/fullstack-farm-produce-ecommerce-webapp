import * as api from "./utils";

export async function runCron() {
  try {
    const response = await api.get("/subscribe/run-cron");
    return response;
  } catch (error) {
    throw error;
  }
}
