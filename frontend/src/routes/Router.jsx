import { HomePage } from "../pages/HomePage/HomePage";
import LoginPage from "../pages/LoginPage/LoginPage";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Layout from "../common/Layout/Layout";
import SignUp from "../pages/SignUpPage/SignUpPage";
import ForgotPassword from "../pages/ForgotPassword/ForgotPassword";
import VerifyEmail from "../pages/VerifyEmail/VerifyEmail";
import ResetPassword from "../pages/ResetPassword/ResetPassword";
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

      ],
    },
  ]);

  return <RouterProvider router={BrowserRoutes} />;
};

export default Router;
