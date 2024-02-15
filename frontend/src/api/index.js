import * as authApi from './auth';
import * as farmApi from './farm';
import * as walletApi from "./wallet";

const api = {
  auth: authApi,
  wallet: walletApi,
  farm: farmApi
};

export default api;