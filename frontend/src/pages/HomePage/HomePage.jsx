import React from "react";
import { Carousel } from "react-responsive-carousel";
import {
    background_1,
    BannerImage,
    CAT1,
    CAT2,
    CAT3,
    farm1
} from "../../assets/images/index";
import "./styles.css";

export const HomePage = () => {
  const list = [1, 2, 3, 4];
  return (
    <>
      <div class="container topbar bg-primary py-3 px-3 rounded d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">123 Street, New York</a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>
                    </div>
                    <div class="top-link pe-10 d-flex ">
                        <a href="#"><small class="text-white mx-2 ">Home</small></a>
                        <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle text-white mx-2" data-bs-toggle="dropdown" aria-expanded="true">Pages</a>
                                <div class="dropdown-menu m-0 bg-secondary rounded-0">
                                    <a href="/LoginPage.jsx" class="dropdown-item">LogIn</a>
                                    <a href="/LoginPage.jsx" class="dropdown-item">LogIn</a>
                                    <a href="/LoginPage.jsx" class="dropdown-item">LogIn</a>
                                    <a href="404.html" class="dropdown-item">404 Page</a>
                                </div>
                        </div>
                        <a href="#"><small class="text-white mx-2">Shop</small></a>
                        <a href="#"><small class="text-white mx-2">Shop Details</small></a>
                        <a href="contact.html" class="nav-item nav-link text-white mx-2">Contact</a>
                    </div>
                </div>
      </div>
        <div class="container-fluid py-5 mb-5 hero-header">
            <div class="container py-5">
                <div class="row g-7 align-items-center">
                    <div class="col-lg-7 col-lg-7">
                        <h4 class="mb-3 text-primary">100% Organic Foods</h4>
                        <h1 class="mb-5 display-3 text-primary">Organic Veggies & Fruits Foods</h1>
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
                <img src={background_1} alt="Cover image" className="rounded" />
              </div>
              <div className="ratio ratio-21x9">
                <img src={BannerImage} alt="Cover image" className="rounded" />
              </div>
              <div className="ratio ratio-21x9">
                <img src={background_1} alt="Cover image" className="rounded" />
              </div>
            </Carousel>
        </div>
    </div>
        </div>
        <div class="position-relative mx-auto">
                            <button type="submit" class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded text-white h-200 shadow lg" style={{left: '7%'}}>Explore the Farms</button>
        </div>
        <div class="container-fluid fruite py-5">
            <div class="container py-5">
                <div class="tab-class text-center">
                    <div class="row g-4">
                        <div class="col-lg-4 text-start">
                            <h1>Our Farms</h1>
                        </div>
                        <div class="col-lg-8 text-end">
                            <ul class="nav nav-pills d-inline-flex text-center mb-5">
                                <li class="nav-item">
                                    <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-1">
                                        <span class="text-dark" style={{width: '130px'}}>All Products</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="d-flex py-2 m-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-2">
                                        <span class="text-dark" style={{width: '130px'}}>Vegetables</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-3">
                                        <span class="text-dark" style={{width: '130px'}}>Fruits</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-4">
                                        <span class="text-dark" style={{width: '130px'}}>Bread</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="d-flex m-2 py-2 bg-light rounded-pill" data-bs-toggle="pill" href="#tab-5">
                                        <span class="text-dark" style={{width: '130px'}}>Meat</span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane fade show p-0 active ">
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="row g-3">
                                        <div class="col-md-5 col-lg-4 col-xl-3 shadow lg ">
                                            <div class="hoverable">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 1</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 2</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 3</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 4</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 5</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 6</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 7</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={farm1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Farm 8</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>About our Farm</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-center flex-lg-wrap">
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Visit the farm</a>
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

        <div class="container-fluid fruite py-1">
            <div class="container py-1">
                <div class="tab-class text-center">
                    <div class="row g-4">
                        <div class="col-lg-4 text-start">
                            <h1>Our Organic Products</h1>
                        </div>
                    </div>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane fade show py-4 active ">
                            <div class="row g-4">
                                <div class="col-lg-12">
                                    <div class="row g-3">
                                        <div class="col-md-5 col-lg-4 col-xl-3 shadow lg ">
                                            <div class="hoverable">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Grapes</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT2} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Grapes</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT3} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Raspberries</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Apricots</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT2} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Banana</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT3} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Oranges</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Raspberries</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT2} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Products</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Grapes</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Fruits</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Grapes</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Fruits</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Apricots</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
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
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Fruits</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Banana</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Fruits</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Raspberries</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-lg-4 col-xl-3 shadow lg">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src={CAT1} class="img-fluid w-100 rounded-top" alt=""></img>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style={{top: '10px', left: '10px'}}>Fruits</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>Oranges</h4>
                                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te incididunt</p>
                                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
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
