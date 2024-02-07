import React from "react";
import { Link } from "react-router-dom";
import { logo } from "../../assets/images";
import Modal from "react-modal";
import { loadStripe } from "@stripe/stripe-js";
import api from "../../api/index";

export const Header = () => {
  let subtitle;

  const [modalIsOpen, setIsOpen] = React.useState(false);
  const [amount, setAmount] = React.useState();

  function closeModal() {
    setIsOpen(false);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(amount);
    const data = await api.auth.walletInit(amount);
    console.log(data);
    const PUBLIC_KEY =
      "pk_test_51J0PUySIJjtkSpgkooDHynUUv6NkOpxarspwxKuhx4ZwNQYx0autnl95jEh2MN5jbmlTDsme1fCf8N65IieDV6LO00YHVh6PS0";

    const stripeTestPromise = await loadStripe(PUBLIC_KEY);
    const { err } = await stripeTestPromise.redirectToCheckout({
      sessionId: data.sessionId,
    });

    // window.location = data.url;
    closeModal();
  };

  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-light bg-white border-bottom">
        <div className="container">
          <a className="navbar-brand">
            <img src={logo} style={{ width: "70px", height: "70px" }} />
            <span className="ms-2 mb-0 h2 text-primary fw-bold">ECOPICK</span>
          </a>
          <div className="d-flex">
            <button onClick={() => setIsOpen(true)}>Open Modal</button>
            <Link to="/login">
              <a className="btn btn-outline-primary d-none d-md-block">Login</a>
            </Link>
            <Link to="/signup">
              <a className="btn btn-primary d-none d-md-block ms-2">Sign up</a>
            </Link>
          </div>
        </div>
      </nav>

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        // style={customStyles}
        contentLabel="Example Modal"
      >
        <div className="container py-3">
          <div className="row my-4">
            <div className="col-md-6 offset-md-3 col-lg-4 offset-lg-4">
              <div className="card border-0 shadow-sm">
                <div className="card-body px-4">
                  <h4 className="card-title fw-bold mt-2 mb-4">Wallet model</h4>
                  <form className="row g-2" onSubmit={handleSubmit}>
                    <div className="col-md-12">
                      <label className="form-label">Amount</label>
                      <input
                        type="text"
                        className="form-control"
                        onChange={(e) => setAmount(e.target.value)}
                      />
                    </div>

                    <div className="col-md-12 mt-4">
                      <button type="submit" className="btn btn-primary w-100">
                        Submit {amount}
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
      </Modal>
    </header>
  );
};

export default Header;
