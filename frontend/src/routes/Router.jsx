import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Layout from "../common/Layout/Layout";
import AddFarm from "../pages/Farm/AddFarm";
import ForgotPassword from "../pages/ForgotPassword/ForgotPassword";
import { HomePage } from "../pages/HomePage/HomePage";
import LoginPage from "../pages/LoginPage/LoginPage";
import ResetPassword from "../pages/ResetPassword/ResetPassword";
import SignUp from "../pages/SignUpPage/SignUpPage";
import VerifyEmail from "../pages/VerifyEmail/VerifyEmail";
import Wallet from "../pages/Wallet/Wallet";

export const Router = () => {
  const BrowserRoutes = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      children: [
        {
          path: "/",
          element: <HomePage />,
        },
        {
          path: "/login",
          element: <LoginPage />,
        },
        {
          path: "/signup",
          element: <SignUp />,
        },
        {
          path: "/forgot-password",
          element: <ForgotPassword />,
        },
        {
          path: "/reset-password",
          element: <ResetPassword />,
        },
        {
          path: "/verify-email",
          element: <VerifyEmail />,
        },
        {
          path: "/wallet",
          element: <Wallet />,
        },
        {
          path: "/add-farm",
          element: <AddFarm />,
        },
        {
          path: "/show-farms",
          element: <ShowFarms />,
        }
        {
          path: "/product-listing",
          element: <ProductListing />,
        },
        {
          path: "/add-product",
          element: <AddProduct />,
        },
        {
          path: "/product",
          element: <ProductDetail />,
        },
        {
          path: "/farmer-products",
          element: <FarmerProductListing />,
        },
        {
          path: "/farmer-product/:id",
          element: <FarmerProductDetail />,
        },
        {
          path: "/farmer-farms",
          element: <FarmerFarms />,
        },
        {
          path: "/farm-detail",
          element: <FarmDetail />,
        },
        {
          path: "/editfarm",
          element: <FarmEdit />,
        },
        {
          path: "/edit-product/:id",
          element: <ProductEdit />,
        },
      ],
    },
  ]);

  return <RouterProvider router={BrowserRoutes} />;
};

export default Router;
