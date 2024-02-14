import React, {useEffect} from "react";
import { Link } from "react-router-dom";
import { logo } from "../../assets/images";
import { loadStripe } from "@stripe/stripe-js";
import Modal from "react-modal";
import api from "../../api/index";
import UserDropdown from "../user-dropdown/UserDropDown";
import { useNavigate } from "react-router-dom";

export const Header = () => {
  const [modalIsOpen, setIsOpen] = React.useState(false);
  const [amount, setAmount] = React.useState();
  const [isLoggedIn, setIsLoggedIn] = React.useState(false);
  const navigate = useNavigate();
  
  useEffect(() => {
   setIsLoggedIn(localStorage.getItem('token') ? true: false);
  }, [])

  function closeModal() {
    setIsOpen(false);
  }

  const handleLogout = () => {
    // Simulate logout by removing the token from localStorage
    setIsLoggedIn(false);
    localStorage.removeItem("token");
    navigate("/");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(amount);
    const data = await api.auth.walletInit(amount);
    console.log(data);
    const PUBLIC_KEY = process.env.REACT_APP_STRIPE_PUBLIC_KEY;

    console.log("PUBLIC_KEY", PUBLIC_KEY);
    const stripeTestPromise = await loadStripe(PUBLIC_KEY);
    const { err } = await stripeTestPromise.redirectToCheckout({
      sessionId: data.sessionId,
    });

    window.location = data.url;
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
            {
              !isLoggedIn? (
                <>
                 <button onClick={() => setIsOpen(true)}>Open Modal</button>
                <Link to="/login">
                  <a className="btn btn-outline-primary d-none d-md-block">Login</a>
                </Link>
                <Link to="/signup">
                  <a className="btn btn-primary d-none d-md-block ms-2">Sign up</a>
                </Link>
                </>
               
              ): (
                <>
                <UserDropdown handleLogout={handleLogout}/> 
                </>
              )
            }
          
          </div>
        </div>
      </nav>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
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
