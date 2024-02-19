import "react-toastify/dist/ReactToastify.css";
import Layout from "../../common/Layout/Layout";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductGridCard from "../../components/ProductGridCard";


function ProductListing() {
  return (
    <div className="vstack">
    <div className="bg-secondary">
      <div className="container">
        <div className="row py-4 px-2">
          <nav aria-label="breadcrumb col-12">
            <ol className="breadcrumb mb-1">
              <li className="breadcrumb-item">
                <a href="#">All Categories</a>
              </li>
              <li className="breadcrumb-item">
                <a href="#">Vegetables</a>
              </li>
              <li className="breadcrumb-item active" aria-current="page">
                Milk
              </li>
            </ol>
          </nav>
        </div>
      </div>
    </div>
    <div className="container py-4">
      <div className="row g-3">
        <div className="col-lg-3">
          <div className="accordion shadow-sm rounded">
            <div className="accordion-item border-bottom">
              <h2 className="accordion-header">
                <button
                  className="accordion-button fw-bold"
                  data-bs-toggle="collapse"
                  data-bs-target="#collapseOne"
                  aria-expanded="true"
                >
                  Categories
                </button>
              </h2>
              <div
                id="collapseOne"
                className="accordion-collapse collapse show"
              >
                <div className="accordion-body pt-2">
                  <div className="vstack gap-2">
                    <a
                      href="#"
                      className="fw-medium link-dark text-decoration-none"
                    >
                      Vegetables
                    </a>
                    <a
                      href="#"
                      className="fw-medium link-dark text-decoration-none"
                    >
                      Milk
                    </a>
                    <a
                      href="#"
                      className="fw-medium link-dark text-decoration-none"
                    >
                     Eggs
                    </a>
                    <a
                      href="#"
                      className="fw-medium link-dark text-decoration-none"
                    >
                      Meat
                    </a>
                    <a
                      href="#"
                      className="fw-medium link-dark text-decoration-none"
                    >
                      Fruits
                    </a>
                  </div>
                </div>
              </div>
            </div>
        
          </div>
        </div>
        <div className="col-lg-9">
          <div className="hstack justify-content-between mb-3">
            <span className="text-dark">33 Items found</span>
            <div className="btn-group" role="group">
              <button className="btn btn-outline-dark">
                <FontAwesomeIcon icon={["fas", "sort-amount-up"]} />
              </button>
              <button className="btn btn-outline-dark">
                <FontAwesomeIcon icon={["fas", "th-list"]} />
              </button>
            </div>
          </div>
          <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
            <div className="col">
              <ProductGridCard />
            </div>
            <div className="col">
              <ProductGridCard off={10} />
            </div>
            <div className="col">
              <ProductGridCard />
            </div>
            <div className="col">
              <ProductGridCard />
            </div>
            <div className="col">
              <ProductGridCard />
            </div>
            <div className="col">
              <ProductGridCard off={25} />
            </div>
            <div className="col">
              <ProductGridCard />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  );
}

ProductListing.getLayout = (page) => {
  return (
    <Layout simpleHeader hideAuth>
      {page}
    </Layout>
  );
};

export default ProductListing;
