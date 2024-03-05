import { useEffect, useState } from "react";
import { Link, useNavigate,useLocation } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useRecoilState } from "recoil";
import DropzoneComponent from "../../components/DropzoneComponent";
import { productState } from "../../recoil/atoms/product";

function ProductEdit() {
  const location = useLocation();
  const { state } = location;
  const { product } = state;
  console.log(product.id);
  const [productName, setProductName] = useState(product.name);
  const [productID, setProductID] = useState(product.productID);
  const [productDescription, setProductDescription] = useState(product.description);
  const [categoryID, setCategoryID]= useState(product.categoryID);
  const [farmID, setFarmID]= useState(product.farmID);
  const [price, setPrice]= useState(product.setPrice);
  const [stock, setStock]= useState(product.setStock);
  const [unit, setUnit]= useState(product.setUnit);
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);
  const [productData, setProductData] = useRecoilState(productState);
  const [files, setFiles] = useState([]);

  useEffect(() => {
    setProductData((prevProductData) => ({
      ...prevProductData,
      productName: productName,
      productDescription: productDescription,
      price: price,
      stock: stock,
      unit: unit,
      farm_id: farmID,
      category_id: categoryID,
    }));
  }, []);
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      setIsLoading(true);
      const formData = new FormData();

      files.forEach((file) => {
        formData.append(`files`, file);
      });
      formData.append("id", product.id);
      formData.append("name", productName);
      formData.append("Description", productDescription);
      formData.append("category_id", categoryID);
      formData.append("farm_id", farmID);
      formData.append("price", parseFloat(price));
      formData.append("stock", stock);
      formData.append("unit", unit);

      const token = localStorage?.getItem("token");
      console.log("token", token);
      const headers = {
        Authorization: `Bearer ${token}`,
      };
      const response = await fetch(
        `${process.env.REACT_APP_BASE_URL}/product/editproduct`,
        {
          method: "POST",
          headers: headers,
          body: formData,
        }
      );
      navigate("/farmer-farms");
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

  return (
    <div className="container py-3">
      <ToastContainer />
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-1 shadow-sm">
            <div className="card-body">
              <form className="row g-3">
                <h4 className="fw-bold py-1 mb-0 row justify-content-center">Edit your product details</h4>

                <div className="col-md-4 fw-semibold">
                  <label className="form-label ">Product ID</label>
                  <input
                    type="text"
                    className="form-control"
                    name="productID"
                    value={product.id}
                    onChange={(e) => {
                      setProductID(e.target.value);
                      setProductData((prevProductData) => ({
                        ...prevProductData,
                        product_id: productID, // Update address in Recoil state
                      }));
                    }}
                  />
                </div>

                <div className="col-md-4 fw-semibold">
                  <label className="form-label">Farm ID</label>
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
                      <a className="btn btn-primary">Update</a>
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

export default ProductEdit;
