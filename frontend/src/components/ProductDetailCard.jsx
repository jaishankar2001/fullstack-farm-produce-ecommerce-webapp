import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

function ProductDetailCard({ id }) {
  return (
    <div className="d-flex py-2">
      <div className="flex-shink-0" style={{ height: 80 }}>
        <img
          className="rounded"
          src={`https://source.unsplash.com/random/100x100?random=${Math.floor(
            Math.random() * 50
          )}`}
          width={80}
          height={80}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="d-flex flex-column flex-grow-1 ms-3">
        {/* <Link href="/product/1">
          <a className="text-dark text-decoration-none">Product name here</a>
        </Link> */}
        <h6 className="mb-0 fw-semibold">10000Ks</h6>
        <div className="mt-auto"></div>
      </div>
    </div>
  );
}

export default ProductDetailCard;
