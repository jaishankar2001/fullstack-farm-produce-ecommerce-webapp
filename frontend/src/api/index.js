import * as authApi from './auth';
import * as farmApi from './farm';
import * as walletApi from "./wallet";
import * as productApi from "./products";

const api = {
  auth: authApi,
  wallet: walletApi,
  farm: farmApi,
  products: productApi
};

export default api;
