import moment from "moment";

import React, { useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { loadStripe } from "@stripe/stripe-js";
import api from "../../api/index";

function Wallet() {
  const [amount, setAmount] = React.useState();
  const [history, sethistory] = React.useState([]);

  console.log(history.length);
  useEffect(() => {
    const fetchHistory = async () => {
      try {
        const data = await api.wallet.getWalletHistory();
        sethistory(data);
        console.log(data);
      } catch (error) {
        console.error("Error verifying email:", error);
      }
    };

    fetchHistory();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(amount);
    const data = await api.auth.walletInit(amount);
    console.log(data);
    const PUBLIC_KEY =
      "pk_test_51J0PUySIJjtkSpgkooDHynUUv6NkOpxarspwxKuhx4ZwNQYx0autnl95jEh2MN5jbmlTDsme1fCf8N65IieDV6LO00YHVh6PS0";

    console.log("PUBLIC_KEY", PUBLIC_KEY);
    const stripeTestPromise = await loadStripe(PUBLIC_KEY);
    const { err } = await stripeTestPromise.redirectToCheckout({
      sessionId: data.sessionId,
    });

    window.location = data.url;
  };

  const getQtyInput = () => {
    return (
      <div className="input-group input-group-sm" style={{ width: 100 }}>
        <button className="btn btn-outline-primary" type="button">
          <FontAwesomeIcon icon={["fas", "minus"]} />
        </button>
        <input
          type="text"
          className="form-control text-center border-primary"
          placeholder=""
          defaultValue="1"
          size="2"
        />
        <button className="btn btn-outline-primary" type="button">
          <FontAwesomeIcon icon={["fas", "plus"]} />
        </button>
      </div>
    );
  };
  return (
    <>
      <div className="container py-4">
        <div className="row g-3">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm">
              <div className="card-header bg-white">
                <h5 className="my-2">Wallet History</h5>
              </div>
              <div className="card-body p-2">
                {/* <CartItem />
              <hr className="text-muted my-1" />
              <CartItem />
              <hr className="text-muted my-1" />
              <CartItem /> */}
                <div className="table-responsive">
                  <table className="table table-borderless align-middle mb-0">
                    <thead>
                      <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Reference number</th>
                      </tr>
                    </thead>
                    <tbody>
                      {history.length > 0 &&
                        history.map((wallet) => {
                          return (
                            <tr>
                              <td scope="row">
                                <div className="hstack">
                                  <div className="ms-3">
                                    <span className="h5">
                                      {moment(wallet.createdAt).format(
                                        "MM/DD/YYYY"
                                      )}
                                    </span>
                                  </div>
                                </div>
                              </td>
                              <td>
                                <h6 className="mb-0">{wallet.amount_Added}</h6>
                              </td>
                              <td>
                                <h6 className="mb-0">
                                  {wallet.paymnent_Method_Reference}
                                </h6>
                              </td>
                            </tr>
                          );
                        })}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="card mb-3 border-0 shadow-sm">
              <div className="card-body">
                <form className="row g-2" onSubmit={handleSubmit}>
                  <div className="input-group">
                    <input
                      className="form-control"
                      type="text"
                      onChange={(e) => setAmount(e.target.value)}
                      placeholder="Add amount to wallet"
                    />
                    <button type="submit" className="btn btn-primary">
                      Add amount
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <br />
        <br />
        <br />
      </div>
    </>
  );
}

export default Wallet;
