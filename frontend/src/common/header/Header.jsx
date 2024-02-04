import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Link } from "react-router-dom";

export const Header = () => {
  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-light bg-white border-bottom">
        <div className="container">
          <a className="navbar-brand">
            <span className="ms-2 mb-0 h4 text-primary fw-bold">ECOPICK</span>
          </a>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav">
              <li className="nav-item">
                <Link to="/">
                <a className="nav-link">Home</a>
                </Link>
              </li>
              <li className="nav-item">
                <a className="nav-link">Products</a>
              </li>
            </ul>
            <ul className="ms-auto navbar-nav">
              <li className="nav-item dropdown">
                <a
                  href="#"
                  className="nav-link dropdown-toggle"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  id="languageMenuLink"
                >
                  English
                </a>
                <ul
                  className="dropdown-menu dropdown-menu-macos dropdown-menu-end"
                  aria-labelledby="languageMenuLink"
                >
                  <li>
                    <a href="#" className="dropdown-item">
                      English
                    </a>
                  </li>
                  <li>
                    <a href="#" className="dropdown-item mt-1">
                      Myanmar
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
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
