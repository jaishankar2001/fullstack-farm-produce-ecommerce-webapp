import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";
import Layout from "../../common/Layout/Layout";
import api from "../../api/index";
import DropzoneComponent from "../../components/DropzoneComponent";
import MapComponent from "../../components/MapComponent";

function AddFarm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      setIsLoading(true);
      const response = await api.auth.login({ email, password });

      if (response) {
        setIsLoading(false);
        // Store tokens in local storage
        localStorage.setItem("token", response.token);
        localStorage.setItem("refreshToken", response.refreshToken);
      }

      navigate("/");
    } catch (error) {
      setIsLoading(false);
      if (
        error.response &&
        error.response.data &&
        error.response.data.message
      ) {
        toast.error(error.response.data.message);
      } else {
        toast.error("An error occurred. Please try again later.");
      }
    }
  };

  return (
    <div className="container py-3">
      <ToastContainer />
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <form className="row g-3">
                <h4 className="fw-semibold mb-0">Contact Info</h4>
                <div className="col-md-6">
                  <label className="form-label">First Name</label>
                  <input type="text" className="form-control" />
                </div>
                <div className="col-md-6">
                  <label className="form-label">Last Name</label>
                  <input type="text" className="form-control" />
                </div>
                <div className="col-md-6">
                  <label className="form-label">Phone</label>
                  <div className="input-group">
                    <div>
                      <select className="form-select rounded-0 rounded-start bg-light">
                        <option>+95</option>
                      </select>
                    </div>
                    <input type="tel" className="form-control" />
                  </div>
                </div>
                <div className="col-md-6">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    className="form-control"
                    placeholder="name@domain.com"
                  />
                </div>

                <div className="col-md-12">
                  <hr className="text-muted mb-0" />
                </div>

                <h4 className="fw-semibold mb-0">Shipping Info</h4>
                <div className="col-md-12">
                  <label className="form-label">Address</label>
                  <input type="text" className="form-control" />
                </div>

                <div className="col-md-12">
                  <DropzoneComponent />
                </div>

                <div className="col-md-12 mt-4">
                  <div className="d-grid gap-2 d-flex justify-content-end">
                    <Link href="/shopping-cart">
                      <a className="btn btn-outline-primary">Cancel</a>
                    </Link>
                    <Link href="/checkout/payment-info">
                      <a className="btn btn-primary">Continue</a>
                    </Link>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div className="col-lg-4">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <MapComponent />
            </div>
          </div>
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

export default AddFarm;
