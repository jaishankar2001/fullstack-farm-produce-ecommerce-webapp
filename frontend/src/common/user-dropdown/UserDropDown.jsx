import React, { useState } from 'react';
import './UserDropdown.css'; 
import { User } from "../../assets/images";
import { useNavigate } from "react-router-dom";


const UserDropdown = ({handleLogout}) => {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  const handleToggle = () => {
    setIsOpen(!isOpen);
  };

  const handleItemClick = (item) => {
    setIsOpen(false); // Close dropdown after item click
  };

  return (
    <div className="user-dropdown">
      <button className="dropdown-button" onClick={handleToggle}>
      <img src={User} style={{ width: "30px", height: "30px" }} />
      </button>
      {isOpen && (
        <ul className="dropdown-list">
          <li onClick={() => {setIsOpen(false); navigate("/add-product");}}>Start Selling</li>
          <li onClick={() => {setIsOpen(false); navigate("/wallet");}}>Wallet</li>
          <li onClick={handleLogout}>Logout</li>
        </ul>
      )}
    </div>
  );
};

export default UserDropdown;
