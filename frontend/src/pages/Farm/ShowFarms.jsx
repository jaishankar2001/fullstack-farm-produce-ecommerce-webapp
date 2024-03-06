import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useNavigate } from "react-router-dom";
import DropzoneComponent from "../../components/DropzoneComponent";

import axios from "axios";
import Map from "../../components/Map";

function ShowFarms(){
  const [farmName, setFarmName] = useState("");
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const [farmData, setFarmData] = useState([]);
  let [number, setNumber] = useState(5);
  const [farmLoc, setFarmLoc] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState({
    lat: 44.6475811,
    lng: -63.5727683,
  });

  const changeFarmLoc = () => {
    setFarmLoc((prevFarmLoc) => {
      let newFarmLoc = [];
      for (let i = 0; i < farmData.length; i++) {
        const id = farmData[i].id;
        const lat = farmData[i].lat;
        const long = farmData[i].lng;
        newFarmLoc.push({ id, lat, long });
      }
      return newFarmLoc;
    });
  }
    
  const handleInputChange = (e) => {
    setFarmName(e.target.value);
  };
  const fetchData = async () => {
    try {
      setIsLoading(true);
      const token = localStorage?.getItem("token");
      const config = {
        headers: {

            'Authorization': `Bearer ${token}`,
        },
        params: {
            "farmName": `${farmName}`
        }
    }

      const responseFromBackend = axios.get("http://localhost:8080/api/customer/listfarms", config);
      setFarmData((await responseFromBackend).data);
    } catch (error) {
      console.log(error);
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
  useEffect(() => {
    changeFarmLoc();
  }, [farmData]);
  useEffect(() => {
    fetchData();
  }, []);
  const handleSubmit = (event) => {
    event.preventDefault();
    fetchData();
  };
  

  return(
    <>
    <div class="container topbar bg-primary py-4 px-4 rounded d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white fw-bold">ecopick@gmail.com</a></small>
                    </div>
                    <div class="top-link pe-10 d-flex ">
                        <a href="#"><small class="text-white mx-2 fw-bold">Home</small></a>
                        <a href="#"><small class="text-white mx-2 fw-bold">Farms</small></a>
                        <a href="#"><small class="text-white mx-2 fw-bold">Products</small></a>
                        <a href="#"><small class="text-white mx-2 fw-bold">Contact</small></a>
                    </div>
                </div>
            </div>
      <div className="container py-3">
      <ToastContainer />
      <div className="row my-2">
        <div className="col-md-6 offset-md-1 col-lg-4 offset-lg-4">
          <div className="card border-0 shadow-sm">
            <div className="card-body px-4">
              <form className="row g-2" onSubmit={handleSubmit}>
                <div className="col-md-12">
                  <input
                    className="form-control"
                    placeholder="type the farm name here"
                    value={farmName}
                    onChange={(e) => setFarmName(e.target.value)}
                  />
                </div>
                <div className="col-md-12 mt-4">
                  <button
                    type="submit"
                    className="btn btn-primary w-100"
                  >submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container-fluid fruite py-1">
        <div class="container py-2">
            <div class="tab-class text-center">
                <div class="row g-2">
                    <div class="col-lg-4 text-start">
                        <h1>Farms</h1>
                    </div>
                </div>
                <div class="tab-content">
                     <div id="tab-1" class="tab-pane fade show p-0 active ">
                        <div class="row g-4">
                            <div  class="col-lg-6">
                                <div class="row g-3">
                                {farmData.length > 0 ? (farmData.map(farm =>
                                    <div key = {farm.id} class='col-md-6 col-lg-12 col-xl-6 shadow lg '>
                                        <div class="hoverable">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm.images[0].img_url} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{ top: '10px', left: '10px' }}>{farm.id}</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>{farm.name}</h4>
                                                    <p>{farm.address}</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    )):""}
                                </div>
                            </div>
                            <div class="col-lg-6">
                            <Map
                              farmLoc={farmLoc}
                              setSelectedLocation={setSelectedLocation}
                              selectedLocation={selectedLocation} />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </>
  );
}
export default ShowFarms;