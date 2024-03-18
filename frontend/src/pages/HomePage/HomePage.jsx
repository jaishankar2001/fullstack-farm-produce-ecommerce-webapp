import React, { useEffect, useState } from "react";
import { Carousel } from "react-responsive-carousel";
import api from "../../api/index";
import {
  BannerImage,
  CAT1,
  CarousalImage,
  FarmCarousal,
  background_1
} from "../../assets/images/index";
import "./styles.css";

export const HomePage = () => {
  const list = [1, 2, 3, 4];
  const [homeFarms, setHomeFarms] = useState([]);
  const [homeProducts, setHomeProducts] = useState([]);
  useEffect(() => {
    const getHomeMeta = async () => {
      const response = await api.home.getHomeMeta();
      console.log(response.farms);
      setHomeFarms(response.farms);

      setHomeProducts(response.products);
    };
    console.log("In useeffect");

    getHomeMeta();
  }, []);
  return (
    <>
      <div class="container topbar bg-primary py-4 px-4 rounded d-none d-lg-block">
        <div class="d-flex justify-content-between">
          <div class="top-info ps-2">
            <small class="me-3">
              <i class="fas fa-envelope me-2 text-secondary"></i>
              <a href="#" class="text-white fw-bold">
                ecopick@gmail.com
              </a>
            </small>
          </div>
          <div class="top-link pe-10 d-flex ">
            <a href="#">
              <small class="text-white mx-2 fw-bold">Home</small>
            </a>
            <a href="/show-farms">
              <small class="text-white mx-2 fw-bold">Farms</small>
            </a>
            <a href="/product-listing">
              <small class="text-white mx-2 fw-bold">Products</small>
            </a>
            <a href="#">
              <small class="text-white mx-2 fw-bold">Contact</small>
            </a>
            <a href="/Admin-dashboard">
              <small class="text-white mx-2 fw-bold">Admin</small>
            </a>
          </div>
        </div>
      </div>
      <div class="container-fluid mb-5 hero-header">
        <div
          class="container py-5"
          style={{
            backgroundImage: `url(${BannerImage})`,
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
        >
          <div
            style={{
              backdropFilter: "blur(15px)",
              backgroundColor: "rgba(255, 255, 255, 0.5)",
            }}
            class="container py-5"
          >
            <div class="container py-5">
              <div class="row g-7 align-items-center">
                <div class="col-lg-7 col-lg-7">
                  <h4 class="mb-3 text-primary fw-bold">100% Organic Foods</h4>
                  <h1 class="mb-5 display-3 text-primary">
                    Organic Vegetables & Fruits{" "}
                  </h1>
                </div>
                <div className="col-lg-5 col-lg-5">
                  <Carousel
                    autoPlay={true}
                    infiniteLoop={true}
                    showArrows={false}
                    showStatus={false}
                    showThumbs={false}
                    transitionTime={500}
                  >
                    <div className="ratio ratio-21x9">
                      <img
                        src={FarmCarousal}
                        alt="Cover image"
                        className="rounded"
                      />
                    </div>
                    <div className="ratio ratio-21x9">
                      <img
                        src={CarousalImage}
                        alt="Cover image"
                        className="rounded"
                      />
                    </div>
                    <div className="ratio ratio-21x9">
                      <img
                        src={background_1}
                        alt="Cover image"
                        className="rounded"
                      />
                    </div>
                  </Carousel>
                </div>
              </div>
              <div class="position-relative mx-auto">
                <button
                  type="submit"
                  class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded text-white h-200 shadow lg"
                >
                  Explore the Farms
                </button>
              </div>
            </div>
          </div>
        </div>
        <div class="container-fluid fruite py-5">
          <div class="container py-5">
            <div class="tab-class text-center">
              <div class="row g-4">
                <div class="col-lg-4 text-start">
                  <h1>Our Farms</h1>
                </div>
              </div>
              <div class="tab-content">
                <div id="tab-1" class="tab-pane fade show p-0 active ">
                  <div class="row g-4">
                    <div class="col-lg-12">
                      <div class="row g-3">
                        {homeFarms.length > 0 &&
                          homeFarms.map((farm) => {
                            return (
                              <div class="col-md-6 col-lg-4 col-xl-3 shadow lg ">
                                <div class="hoverable">
                                  <div class="rounded position-relative fruite-item">
                                    <div class="fruite-img">
                                      {farm.images && (
                                        <img
                                          src={farm.images[0]?.img_url}
                                          class="img-fluid w-100 rounded-top"
                                          alt=""
                                        ></img>
                                      )}
                                    </div>
                                    <div
                                      class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                      style={{ top: "10px", left: "10px" }}
                                    >
                                      {farm.name}
                                    </div>
                                    <div class="p-4 rounded-bottom">
                                      <h4>About our Farm</h4>
                                      <p>{farm.description}</p>
                                      <div class="d-flex justify-content-center flex-lg-wrap">
                                        <a
                                          href="#"
                                          class="btn border rounded-pill px-3 text-primary"
                                        >
                                          <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                          Visit the farm
                                        </a>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            );
                          })}
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="container-fluid fruite py-1">
          <div class="container py-1">
            <div class="tab-class text-center">
              <div class="row g-4">
                <div class="col-lg-4 text-start">
                  <h1>Our Organic Products</h1>
                </div>
                {/* <div class="col-lg-8 text-end">
                  <ul class="nav nav-pills d-inline-flex text-center mb-5">
                    <li class="nav-item">
                      <a
                        class="d-flex m-2 py-2 bg-light rounded-pill"
                        data-bs-toggle="pill"
                        href="#tab-1"
                      >
                        <span class="text-dark" style={{ width: "130px" }}>
                          All Products
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a
                        class="d-flex py-2 m-2 bg-light rounded-pill"
                        data-bs-toggle="pill"
                        href="#tab-2"
                      >
                        <span class="text-dark" style={{ width: "130px" }}>
                          Vegetables
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a
                        class="d-flex m-2 py-2 bg-light rounded-pill"
                        data-bs-toggle="pill"
                        href="#tab-3"
                      >
                        <span class="text-dark" style={{ width: "130px" }}>
                          Fruits
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a
                        class="d-flex m-2 py-2 bg-light rounded-pill"
                        data-bs-toggle="pill"
                        href="#tab-4"
                      >
                        <span class="text-dark" style={{ width: "130px" }}>
                          Bread
                        </span>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a
                        class="d-flex m-2 py-2 bg-light rounded-pill"
                        data-bs-toggle="pill"
                        href="#tab-5"
                      >
                        <span class="text-dark" style={{ width: "130px" }}>
                          Meat
                        </span>
                      </a>
                    </li>
                  </ul>
                </div> */}
              </div>
              <div class="tab-content">
                <div id="tab-1" class="tab-pane fade show py-4 active ">
                  <div class="row g-4">
                    <div class="col-lg-12">
                      <div class="row g-3">
                        {homeProducts.length > 0 &&
                          homeProducts.map((product) => {
                            console.log("productproduct", product);
                            return (
                              <div class="col-md-5 col-lg-4 col-xl-3 shadow lg ">
                                <div class="hoverable">
                                  <div class="rounded position-relative fruite-item">
                                    <div class="fruite-img">
                                      {product.images && (
                                        <img
                                          src={product.images[0].img_url}
                                          class="img-fluid w-100 rounded-top"
                                          alt=""
                                        ></img>
                                      )}
                                    </div>
                                    <div
                                      class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                      style={{ top: "10px", left: "10px" }}
                                    >
                                      {product.productName}
                                    </div>
                                    <div class="p-4 border-top-0 rounded-bottom">
                                      <p>{product.productDescription}</p>
                                      <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">
                                          ${product.price} / {product.unit}
                                        </p>
                                        <a
                                          href="#"
                                          class="btn border border-secondary rounded-pill px-3 text-primary"
                                        >
                                          <i class="fa fa-shopping-bag me-2 text-primary"></i>
                                          View Product
                                        </a>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            );
                          })}
                      </div>
                    </div>
                  </div>
                </div>
                <div id="tab-4" class="tab-pane fade show p-0">
                  <div class="row g-4">
                    <div class="col-lg-12">
                      <div class="row g-4">
                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img
                                src={CAT1}
                                class="img-fluid w-100 rounded-top"
                                alt=""
                              ></img>
                            </div>
                            <div
                              class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style={{ top: "10px", left: "10px" }}
                            >
                              Fruits
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4>Grapes</h4>
                              <p>
                                Our grapes boast unparalleled flavor, grown with
                                care using organic practices on our family farm.
                              </p>
                              <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">
                                  $4.99 / kg
                                </p>
                                <a
                                  href="#"
                                  class="btn border border-secondary rounded-pill px-3 text-primary"
                                >
                                  <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                  View Product
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img
                                src={CAT1}
                                class="img-fluid w-100 rounded-top"
                                alt=""
                              ></img>
                            </div>
                            <div
                              class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style={{ top: "10px", left: "10px" }}
                            >
                              Fruits
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4>Apricots</h4>
                              <p>
                                Our apricots boast unparalleled flavor, grown
                                with care using organic practices on our family
                                farm.
                              </p>
                              <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">
                                  $4.99 / kg
                                </p>
                                <a
                                  href="#"
                                  class="btn border border-secondary rounded-pill px-3 text-primary"
                                >
                                  <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                  View Product
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div id="tab-5" class="tab-pane fade show p-0">
                  <div class="row g-4">
                    <div class="col-lg-12 ">
                      <div class="row g-4">
                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img
                                src={CAT1}
                                class="img-fluid w-100 rounded-top"
                                alt=""
                              ></img>
                            </div>
                            <div
                              class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style={{ top: "10px", left: "10px" }}
                            >
                              Fruits
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4>Banana</h4>
                              <p>
                                Our banana boast unparalleled flavor, grown with
                                care using organic practices on our family farm.
                              </p>
                              <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">
                                  $4.99 / kg
                                </p>
                                <a
                                  href="#"
                                  class="btn border border-secondary rounded-pill px-3 text-primary"
                                >
                                  <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                  Add to cart
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img
                                src={CAT1}
                                class="img-fluid w-100 rounded-top"
                                alt=""
                              ></img>
                            </div>
                            <div
                              class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style={{ top: "10px", left: "10px" }}
                            >
                              Fruits
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4>Raspberries</h4>
                              <p>
                                Our raspberries boast unparalleled flavor, grown
                                with care using organic practices on our family
                                farm.
                              </p>
                              <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">
                                  $4.99 / kg
                                </p>
                                <a
                                  href="#"
                                  class="btn border border-secondary rounded-pill px-3 text-primary"
                                >
                                  <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                  Add to cart
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                          <div class="rounded position-relative fruite-item">
                            <div class="fruite-img">
                              <img
                                src={CAT1}
                                class="img-fluid w-100 rounded-top"
                                alt=""
                              ></img>
                            </div>
                            <div
                              class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                              style={{ top: "10px", left: "10px" }}
                            >
                              Fruits
                            </div>
                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                              <h4>Oranges</h4>
                              <p>
                                Our oranges boast unparalleled flavor, grown
                                with care using organic practices on our family
                                farm.
                              </p>
                              <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">
                                  $4.99 / kg
                                </p>
                                <a
                                  href="#"
                                  class="btn border border-secondary rounded-pill px-3 text-primary"
                                >
                                  <i class="fa fa-shopping-bag me-2 text-primary"></i>{" "}
                                  Add to cart
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
