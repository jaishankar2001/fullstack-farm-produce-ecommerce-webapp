import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";
import DropzoneComponent from "../../components/DropzoneComponent";
import MapComponent from "../../components/MapComponent";
import { farmState } from "../../recoil/atoms/farm"; 
import { useRecoilState } from "recoil";

function AddFarm() {
  const [farmName, setFarmName] = useState("");
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const [farmData, setFarmData] = useRecoilState(farmState); 
  const [files, setFiles] = useState([]);
  console.log(farmData);
  console.log(files[0], 'files');


  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      setIsLoading(true);
      const formData = new FormData();
      formData.append("files", files);
      formData.append("Address", farmData.Address);
      formData.append("lat", farmData.lat);
      formData.append("lng", farmData.lng);

      const token = localStorage?.getItem('token');
      const headers = {
        'Content-Type': 'multipart/form-data',
        'Authorization': `Bearer ${token}`
      };
      const response = await fetch("http://localhost:8080/api/farmer/addfarm", {
        method: 'POST',
        headers: headers,
        body: formData,
      });

      // const response = await api.farm.addFarm(formData);  
      if (response) {
        setIsLoading(false);
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

  const handleFilesSelected = (selectedFiles) => {
    setFiles(selectedFiles);
  };

  return (
    <div className="container py-3">
      <ToastContainer />
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <form className="row g-3">
                <h4 className="fw-bold mb-0">Register Your Farm</h4>
                <div>
                  <label className="form-label">Farm Name</label>
                  <input type="text" className="form-control" 
                    value={farmName}
                    onChange={(e) => {setFarmName(e.target.value);  setFarmData((prevFarmData) => ({
                      ...prevFarmData,
                      name: farmName, // Update address in Recoil state
                    }));}}/>
                </div>
                <div className="col-md-12">
                  <hr className="text-muted mb-0" />
                </div>
                <h6 className="fw-semibold mb-0">Contact Info</h6>
                <div className="col-md-6">
                  <label className="form-label">Phone</label>
                  <div className="input-group">
                    <div>
                      <select className="form-select rounded-0 rounded-start bg-light">
                        <option>+1</option>
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

                <h6 className="fw-semibold mb-0">About your Farm</h6>
                <div>
                  <input type="textarea" className="form-control" />
                </div>

                <div className="col-md-12">
                  <hr className="text-muted mb-0" />
                </div>

                <h6 className="fw-semibold mb-0">Add Farm Images</h6>
                <div className="col-md-12">
                  <DropzoneComponent onFilesSelected={handleFilesSelected} />
                </div>

                <div className="col-md-12 mt-4">
                  <div className="d-grid gap-2 d-flex justify-content-end">
                    <Link href="/shopping-cart">
                      <a className="btn btn-outline-primary">Cancel</a>
                    </Link>
                    <Link href="/" onClick={handleSubmit}>
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
