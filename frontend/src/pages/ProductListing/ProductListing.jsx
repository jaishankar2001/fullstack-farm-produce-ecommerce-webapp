import React, { useState, useEffect } from "react";
import "react-toastify/dist/ReactToastify.css";
import Layout from "../../common/Layout/Layout";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductGridCard from "../../components/ProductGridCard";
import api from "../../api/index";

function ProductListing() {
  const [selectedTab, setSelectedTab] = useState("allProducts");
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [allProducts, setAllProducts] = useState([]);

  const getAllProducts = async () => {
    const response = await api.products.getProducts({
      productName: searchTerm,
    });
    setAllProducts(response);
  };
  console.log(allProducts);

  useEffect(() => {
    getAllProducts();
  }, [searchTerm]);

  const handleTabChange = (tab) => {
    setSelectedTab(tab);
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleCategoryCheckboxChange = (category) => {
    if (selectedCategories.includes(category)) {
      setSelectedCategories(selectedCategories.filter((c) => c !== category));
    } else {
      setSelectedCategories([...selectedCategories, category]);
    }
  };

  return (
    <div className="vstack">
      <div className="bg-secondary">
        <div className="container">
          <div className="row py-4 px-2">
            <div className="col-lg-7">
              <div className="col-lg-7">
                <div className="input-group">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Search products..."
                    value={searchTerm}
                    onChange={handleSearchChange}
                  />
                  <button className="btn btn-outline-primary" type="button">
                    Search
                  </button>
                </div>
              </div>
              {/* <nav aria-label="breadcrumb">
                <ol className="breadcrumb mb-1">
                  <li className="breadcrumb-item">
                    <a href="#">{selectedTab === "allProducts" ? "All Products" : "My Products"}</a>
                  </li>
                </ol>
              </nav> */}
            </div>
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
                      <label className="form-check">
                        <input
                          type="checkbox"
                          className="form-check-input"
                          checked={selectedCategories.includes("Vegetables")}
                          onChange={() =>
                            handleCategoryCheckboxChange("Vegetables")
                          }
                        />
                        Vegetables
                      </label>
                      <label className="form-check">
                        <input
                          type="checkbox"
                          className="form-check-input"
                          checked={selectedCategories.includes("Milk")}
                          onChange={() => handleCategoryCheckboxChange("Milk")}
                        />
                        Milk
                      </label>
                      {/* Add similar labels for other categories */}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="col-lg-9">
            <div className="hstack justify-content-between mb-3">
              <span className="text-dark">
                {allProducts?.length} Items found
              </span>
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
              {allProducts.map((product, index) => (
                <div className="col">
                  <ProductGridCard product={product} />
                </div>
              ))}
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
