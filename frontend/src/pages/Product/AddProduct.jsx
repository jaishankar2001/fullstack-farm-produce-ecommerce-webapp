import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";
import DropzoneComponent from "../../components/DropzoneComponent";
import { productState } from "../../recoil/atoms/product";

function AddProduct() {
  const [productName, setProductName] = useState("");
  const [categoryID, setCategoryID]= useState("");
  const [farmID, setFarmID]= useState("");
  const [price, setPrice]= useState("");
  const [stock, setStock]= useState("");
  const [unit, setUnit]= useState("");
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const [productData, setProductData] = useRecoilState(productState);
  const [files, setFiles] = useState([]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      setIsLoading(true);
      const formData = new FormData();

      files.forEach((file) => {
        formData.append(`files`, file);
      });
      formData.append("productName", productData.name);
      formData.append("productDescription", productData.description);
      formData.append("price", productData.price);
      formData.append("stock", productData.stock);
      formData.append("unit", productData.unit);
      formData.append("farmID", productData.farm_id);
      formData.append("categoryID", productData.category_id);

      const token = localStorage?.getItem("token");
      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const response = await fetch("http://localhost:8080/api/products/addproduct", {
        method: "POST",
        headers: headers,
        body: formData,
      });

      navigate("/");
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

  const handleFilesSelected = (selectedFiles) => {
    setFiles(selectedFiles);
  };

  const handleChange = (e) => {
    setProductData({ ...productData, [e.target.name]: e.target.value });
  };

  return (
    <div className="container py-3">
      <ToastContainer />
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-1 shadow-sm">
            <div className="card-body">
              <form className="row g-3">
                <h4 className="fw-bold py-1 mb-0 row justify-content-center">Add the product</h4>

                <div className="col-md-4 fw-semibold">
                  <label className="form-label ">Farm ID</label>
                  <input
                    type="text"
                    className="form-control"
                    name="farmID"
                    value={farmID}
                    onChange={(e) => {
                      setFarmID(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        farm_id: farmID, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>
                <div className="col-md-4 fw-semibold">
                  <label className="form-label">Category ID</label>
                  <input
                    type="text"
                    className="form-control"
                    name="categoryID"
                    value={categoryID}
                    onChange={(e) => {
                      setCategoryID(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        category_id: categoryID, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>

                <div className="fw-semibold mb-0">
                  <label className="form-label fw-semibold">Product Name</label>
                  <input
                    type="text"
                    className="form-control"
                    value={productName}
                    onChange={(e) => {
                      setProductName(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        name: productName, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>

                <div className="col-md-4 fw-semibold mb-0">
                  <label className="form-label fw-semibold">Price</label>
                  <input
                    type="text"
                    className="form-control"
                    value={price}
                    onChange={(e) => {
                      setPrice(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        price: price, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>

                <div className="col-md-4 fw-semibold mb-0">
                  <label className="form-label fw-semibold">Stock</label>
                  <input
                    type="text"
                    className="form-control"
                    value={stock}
                    onChange={(e) => {
                      setStock(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        stock: stock, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>

                {/* <div className="col-md-4 fw-semibold mb-0">
                  <label className="form-label fw-semibold">Unit</label>
                  <input
                    type="text"
                    className="form-control"
                    value={unit}
                    onChange={(e) => {
                      setUnit(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        unit: unit, // Update address in Recoil state
                      }));
                    }}
                  />
                </div> */}

                
                <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                    Unit
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="#">lb</a>
                <a class="dropdown-item" href="#">litre</a>
                <a class="dropdown-item" href="#">piece</a>
            </div>
        </div>
        

                <h6 className="fw-semibold mb-0">About your Product</h6>
                <div>
                  <input type="textarea" className="form-control" />
                </div>

                <div className="col-md-12">
                  <hr className="text-muted mb-0" />
                </div>

                <h6 className="fw-semibold mb-0">Add Product Images</h6>
                <div className="col-md-12">
                  <DropzoneComponent onFilesSelected={handleFilesSelected} />
                </div>

                <div className="col-md-12 mt-4">
                  <div className="d-grid gap-2 d-flex justify-content-end">
                    <Link href="/" onClick={handleSubmit}>
                      <a className="btn btn-primary">Submit</a>
                    </Link>
                  </div>
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
  );
}

export default AddProduct;
