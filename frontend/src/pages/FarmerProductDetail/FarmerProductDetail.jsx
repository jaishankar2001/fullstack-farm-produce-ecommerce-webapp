import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
import Modal from "react-modal";
import { useLocation } from "react-router-dom";
import api from "../../api/index";
import { ToastContainer, toast } from "react-toastify";

function FarmerProductDetail() {
  const images = [2, 4, 6, 8, 1];
  const navigate = useNavigate();
  const location = useLocation();
  const { state } = location;
  const { product } = state;
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const openModal = () => {
    setModalIsOpen(true);
  };


  const closeModal = () => {
    setModalIsOpen(false);
  };

  const customStyles = {
    content: {
      top: "50%",
      left: "50%",
      right: "auto",
      bottom: "auto",
      marginRight: "-50%",
      transform: "translate(-50%, -50%)",
    },
  };

  const onDeleteProduct = async() => {
     const response = await api.products.deleteProduct(product.id);
     toast.success("Farm deleted successfully!");
     navigate("/farmer-products");
 
     closeModal();

  }

  return (
    <div className="vstack">
       <ToastContainer />
      <div className="bg-secondary">
        <div className="container">
          <div className="row py-4 px-2"></div>
        </div>
      </div>
      <div className="bg-white mb-4">
        <div className="container py-4">
          <div className="row gy-3 gx-4">
            <div className="col-lg-5">
              <div className="row">
                <div className="col-12">
                  <div className="ratio ratio-1x1">
                    <img
                      className="rounded"
                      src={product.images?.[0]?.img_url}
                      width={150}
                      height={150}
                      alt="Product image."
                    />
                  </div>
                </div>
              </div>
              {/* <div className="row mt-3 d-none d-lg-block">
                <div className="col-12 d-flex justify-content-center">
                  {images.map((img) => {
                    return (
                      (<div
                        key={img}
                        style={{ width: 60 }}
                        className="me-2 ratio ratio-1x1"
                      >
                        <img
                          className="rounded"
                          src={img.img_url}
                          width={60}
                          height={60}
                          alt="Product image."
                          key={img}
                        />
                      </div>)
                    );
                  })}
                </div>
              </div> */}
            </div>

            <div className="col-lg-7">
              <div className="d-flex">
                <div className="d-inline h2 mb-0 fw-semibold me-3">
                  {product.productName}
                </div>
                <div className="ms-auto">
                  <button
                    className="btn btn-outline-secondary text-primary border"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title="Add to wish list"
                  >
                    <FontAwesomeIcon icon={["far", "heart"]} size="lg" />
                  </button>
                </div>
              </div>

              <div className="vstack">
                <div className="d-flex mb-3 gap-2 mt-2">
                  {/* <ProductRating /> */}
                  <span className="text-success small">
                    <FontAwesomeIcon icon={["fas", "check-circle"]} />
                    &nbsp;In Stock
                  </span>
                </div>
                <h4 className="fw-semibold">
                  ${product.price}/{product.unit}
                </h4>
                <p className="fw-light">
                  Lorem ipsum is placeholder text commonly used in the graphic,
                  print, and publishing industries for previewing layouts and
                  visual mockups.
                </p>
                <dl className="row mb-0">
                  <dt className="col-sm-3 fw-semibold">Code#</dt>
                  <dd className="col-sm-9">{product.id}</dd>
                  <dt className="col-sm-3 fw-semibold">Category</dt>
                  <dd className="col-sm-9">{product.id}</dd>
                  <dt className="col-sm-3 fw-semibold">Stock</dt>
                  <dd className="col-sm-9">{product.stock}</dd>
                </dl>
                <hr className="text-muted" />
                <div className="d-flex">
                  <a
                    href="#"
                    className="btn btn-primary px-md-4 col col-md-auto me-2"
                  >
                    Edit Product
                  </a>
                  <button className="btn btn-outline-primary col col-md-auto" onClick={openModal}>
                    <FontAwesomeIcon icon={["fas", "trash"]} />
                    &nbsp;Delete Product
                  </button>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </div>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Delete Confirmation"
        style={customStyles}
      >
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">Are you sure to delete this farm?</h5>
            <button
              className="btn btn-primary"
              onClick={onDeleteProduct}
            >
              Delete
            </button>
          </div>
        </div>
      </Modal>
      <div className="container">
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

export default FarmerProductDetail;
