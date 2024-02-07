import React from "react";
import Layout from "../../common/Layout/Layout";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "./CheckoutForm";

function Payment() {
  const stripePromise = loadStripe(
    "pk_test_51J0PUySIJjtkSpgkooDHynUUv6NkOpxarspwxKuhx4ZwNQYx0autnl95jEh2MN5jbmlTDsme1fCf8N65IieDV6LO00YHVh6PS0"
  );
  const clientSecret =
    "sk_test_51J0PUySIJjtkSpgkwLWSNz2rkkS3FtCOBl9XYeFzCKHrQncLQJws9FodTsc1hL9apjqkGUOuIArdeEkDlxDkJUds004LPirNvP";
  return (
    <>
      <Elements stripe={stripePromise} options={{ clientSecret }}>
        <CheckoutForm />
      </Elements>
    </>
  );
}

Payment.getLayout = (page) => {
  return (
    <Layout simpleHeader hideAuth>
      {page}
    </Layout>
  );
};

export default Payment;
