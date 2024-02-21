import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductDetailCard from "../../components/ProductDetailCard";
import { useLocation } from "react-router-dom";
import { CAT1 } from "../../assets/images/index";

function FarmerProductDetail() {
  const images = [2, 4, 6, 8, 1];
  const location = useLocation();
  const { state } = location;
  const { product } = state;

  return (
    <div className="vstack">
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
                  <button className="btn btn-outline-primary col col-md-auto">
                    <FontAwesomeIcon icon={["fas", "trash"]} />
                    &nbsp;Delete Product
                  </button>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="container">
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

export default FarmerProductDetail;
