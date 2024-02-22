import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductDetailCard from "../../components/ProductDetailCard";
import { useLocation } from 'react-router-dom';
import "./styles.css";
import {
    CAT1,
} from "../../assets/images/index";

function ProductDetail() {
  const images = [2, 4, 6, 8, 1];
  const location = useLocation();
  const { state } = location;
  const { product } = state;

  return (
    <div className="vstack">
      <div className="bg-secondary">
        <div className="container">
          <div className="row py-4 px-2">
          </div>
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
                <h4 className="fw-semibold">${product.price}/{product.unit}</h4>
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
                <div class="attractive-banner">
  <div class="banner-content d-flex align-items-center">
    <div class="text-section">
      <p class="availability-text mt-1">Next Stock available on: <span class="date-highlight">22/02/2024</span></p>
    </div>
    <button class="btn btn-primary btn-pre-book">Pre-book Now</button>
  </div>
</div>
<hr className="text-muted" />
                <div className="d-flex">
                  <a
                    href="#"
                    className="btn btn-primary px-md-4 col col-md-auto me-2"
                  >
                    Buy now
                  </a>
                  <button className="btn btn-outline-primary col col-md-auto">
                    <FontAwesomeIcon icon={["fas", "cart-plus"]} />
                    &nbsp;Add to cart
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="container">
        <div className="row g-3">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm">
              <div
                className="px-3 d-flex border-bottom overflow-auto"
                style={{ height: 70 }}
              >
                <ul className="nav nav-pills my-auto flex-nowrap">
                  <li className="nav-item">
                    <a href="#" className="nav-link active" aria-current="true">
                      Description
                    </a>
                  </li>
                </ul>
              </div>
              <div className="card-body">
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                  Duis ultricies lacus sed turpis tincidunt. Urna cursus eget
                  nunc scelerisque. Sit amet massa vitae tortor condimentum.
                  Amet est placerat in egestas erat. Vel quam elementum pulvinar
                  etiam non quam lacus suspendisse faucibus. Duis at consectetur
                  lorem donec massa sapien faucibus. Leo integer malesuada nunc
                  vel risus commodo viverra maecenas. Pellentesque eu tincidunt
                  tortor aliquam nulla facilisi. Gravida in fermentum et
                  sollicitudin ac. Amet purus gravida quis blandit turpis cursus
                  in hac habitasse. Augue mauris augue neque gravida in
                  fermentum et sollicitudin. Faucibus in ornare quam viverra.
                  Nisl rhoncus mattis rhoncus urna neque viverra justo. Cras
                  semper auctor neque vitae. Nulla facilisi morbi tempus
                  iaculis. Quam vulputate dignissim suspendisse in. Vestibulum
                  rhoncus est pellentesque elit ullamcorper. Suspendisse
                  ultrices gravida dictum fusce ut. Lacus vel facilisis volutpat
                  est velit egestas.
                </p>
              </div>
              <div className="card-footer py-3">
                <small>
                  <FontAwesomeIcon
                    icon={["fas", "truck"]}
                    className="text-success me-2"
                  />
                  Delivery within 1-2 days
                </small>
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="card border-0 shadow-sm">
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

export default ProductDetail;
