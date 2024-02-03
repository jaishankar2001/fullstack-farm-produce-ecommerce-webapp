import React from "react";
import { Carousel } from "react-responsive-carousel";
import {
  background_1,
  BannerImage,
  CAT1,
  CAT2,
  CAT3,
  CAT4,
} from "../../assets/images/index";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Product from "../../components/product";

export const HomePage = () => {
  const list = [1, 2, 3, 4];
  return (
    <>
      <div className="container py-3">
        <div className="row mb-4">
          <div className="col-12">
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

        <div className="row row-cols-1 row-cols-md-3 g-3 mb-4">
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "money-bill-alt"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Reasonable Price</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "headset"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Customer Support 24/7</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "truck"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Fast Delivery</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
        </div>

        <h4 className="mb-3 fw-semibold">New products</h4>
        <div className="row row-cols-1 row-cols-sm-2 row-cols-lg-4 g-3 mb-5">
          <div className="col" key={1}>
            <Product id={1} title={`Product ${1}`} src={CAT1} />
          </div>

          <div className="col">
            <Product id={2} title={`Product ${2}`} src={CAT2} />
          </div>

          <div className="col">
            <Product id={3} title={`Product ${3}`} src={CAT3} />
          </div>

          <div className="col">
            <Product id={4} title={`Product ${4}`} src={CAT4} />
          </div>
        </div>
      </div>
    </>
  );
};
