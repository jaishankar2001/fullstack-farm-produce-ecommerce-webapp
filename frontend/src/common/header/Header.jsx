import React from "react";
import { Link } from "react-router-dom";
import { logo } from "../../assets/images";

export const Header = () => {

  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-light bg-white border-bottom">
        <div className="container">
          <a className="navbar-brand">
            <img src={logo} style={{ width: "70px", height: "70px" }} />
            <span className="ms-2 mb-0 h2 text-primary fw-bold">ECOPICK</span>
          </a>
          <div className="d-flex">
            <Link to="/login">
              <a className="btn btn-outline-primary d-none d-md-block">Login</a>
            </Link>
            <Link to="/signup">
              <a className="btn btn-primary d-none d-md-block ms-2">Sign up</a>
            </Link>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Header;
