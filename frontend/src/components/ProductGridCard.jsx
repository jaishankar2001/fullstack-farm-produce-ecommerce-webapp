import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useNavigate } from "react-router-dom";
import { CAT1 } from "../assets/images/index";

function ProductGridCard({ product }) {
  const navigate = useNavigate();

  return (
    <div className="card h-100 border-0 shadow-sm">
    <a>
      <div className="ratio ratio-1x1 position-relative">
        <img
          className="card-img-top"
          src={product.images?.[0]?.img_url}
          alt="Product image."
        />
      </div>
    </a>
  
    <div className="card-body">
      <div className="vstack gap-2">
        <div>
          <div className="d-flex justify-content-center flex-lg-wrap">
            <h4 className="text-dark fs-5 fw-bold mb-0">{product.productName}</h4>
          </div>
          <div className="d-flex flex-lg-wrap justify-content-center mt-2">
            <p className="text-center">
              Our grapes boast unparalleled flavor, grown with care using organic practices on our family farm.
            </p>
          </div>
        </div>
        <div className="hstack gap-2 justify-content-center">
          <button className="btn btn-outline-secondary text-primary border d-md-block d-lg-none">
            <FontAwesomeIcon icon={["far", "heart"]} />
          </button>
  
          <button
            className="btn btn-sm btn-secondary text-primary flex-grow-1 d-none d-lg-block"
            onClick={() => {
              const currentPath = window.location.pathname;
              console.log(currentPath);
              if(currentPath == "/product-listing"){
                navigate("/product", { state: { product } });
              }else{
                navigate("/farmer-product", { state: { product } });
              }
            }}
          >
            <FontAwesomeIcon icon={["fas", "cart-plus"]} />
            View Product
          </button>
          <div>
      </div>
        </div>
      </div>
    </div>
  </div>
  
  );
}

export default ProductGridCard;
