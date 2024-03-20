import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import "./UserDropdown.css";

const UserDropdown = ({ handleLogout }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [userMeta, setUserMeta] = React.useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    setUserMeta(JSON.parse(localStorage.getItem("userMeta")));
  }, []);

  let currentLocation = useLocation();
  if (currentLocation.pathname === "/Admin-dashboard") {
    return <a className="btn btn-primary d-none d-md-block ms-2" onClick={handleLogout}>Logout</a>;
  }
else{
  return (
    <>
      {userMeta && (
        <ul class="nav navbar-nav ms-auto">
          <li class="nav-item dropdown">
            <a href="#" class="nav-link" data-bs-toggle="dropdown">
              {userMeta?.name} <FontAwesomeIcon icon="fa-solid fa-caret-down" />
            </a>
            <div class="dropdown-menu dropdown-menu-end">
              <Link to="/add-farm">
                <a className="dropdown-item">Start Selling</a>
              </Link>

              <Link to="/farmer-farms">
                <a className="dropdown-item">My farm</a>
              </Link>

              <Link to="/farmer-products">
                <a className="dropdown-item">My products</a>
              </Link>

              <Link to="/wallet">
                <a className="dropdown-item">Wallet</a>
              </Link>

              <Link to="/order-history">
                <a className="dropdown-item">My Orders</a>
              </Link>

              <a class="dropdown-item" onClick={handleLogout}>
                Logout
              </a>
            </div>
          </li>
        </ul>
      )}
    </>
  );
};
}

export default UserDropdown;
