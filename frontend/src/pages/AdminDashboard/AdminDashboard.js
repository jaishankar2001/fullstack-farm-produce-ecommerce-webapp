import React from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import moment from "moment";

const AdminDashboard = () => {
  return (
    <Container fluid className="bg-light">
      <Row>
        <Col md={2} className="bg-primary text-light p-1">
          <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <a class="sidebar-brand d-flex align-items-left justify-content-center row g-1">
                  <div class="col-lg-4 text-start">
                      <i class="fas fa-laugh-wink"></i>
                  </div>
                  <div className="text-light p-2">
                    <h3>Hello Admin,</h3>
                  </div>
              </a>

              {/* <!-- Divider --> */}
              <hr class="sidebar-divider my-0"></hr>

              {/* <!-- Nav Item - Dashboard --> */}
              <a href="/Admin-dashboard" class="nav-item active p-3 text-light">
                      <i class="fas fa-fw fa-tachometer-alt"></i>
                      <span><h5>Dashboard</h5></span>
                      </a>
            </ul>
        </Col>
        <Col md={10} className="py-2">
        <div className="bg-secondary">
        <div className="container mb-2">
          <div className="row py-2 px-1">
            <div className="col-lg-5">
            <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search For..."
                />
                <button className="btn btn-outline-primary" type="button">
                  Search
                </button>
              </div>
              
            </div>
          </div>
        </div>
      </div>
      
          <div class="d-sm-flex align-items-center justify-content-between mb-4 ">
                        <h1 class="h3 mb-0 text-gray-800"> Admin Dashboard</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                          <i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
                    </div>

                <div class="row">
                    <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-1">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Sales
                                            </div>
                                            <div class="h6 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-1">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Number of Users
                                            </div>
                                            <div class="h6 mb-0 text-gray-800">150
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-1">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Number of Farms
                                            </div>
                                            <div class="h6 mb-0 text-gray-800">60
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-1">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Pending orders
                                            </div>
                                            <div class="h6 mb-0 text-gray-800">10
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                            <div class="card col-xl-5 shadow mb-4">
                                <div class="card-header py-3 mb-2">
                                    <h6 class="m-0 font-weight-bold text-primary">Sales</h6>
                                </div>
                            </div>
                    </div>

                    <div class="row">
                    <div class="card col-xl-5 shadow mb-4 ml-1">
                        <div class="card-header py-3 mb-2">
                            <h6 class="m-0 font-weight-bold text-primary mb-3">List of Users</h6>
                            <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                <th scope="col">User ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email ID</th>
                                <th scope="col">Role</th>
                                </tr>
                                </thead>
                            </table>
                            </div>
                        </div>
                    </div>
                    <div class="card col-xl-6 shadow mb-4 ml-1">
                        <div class="card-header py-3 mb-2">
                            <h6 class="m-0 font-weight-bold text-primary mb-3">List of Farms</h6>
                            <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                <th scope="col">Farm ID</th>
                                <th scope="col">Farm name</th>
                                <th scope="col">Owner name</th>
                                <th scope="col">Owner Email</th>
                                </tr>
                                </thead>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="card col-xl-12 shadow mb-4">
                        <div class="card-header py-2 mb-2">
                            <h6 class="m-0 font-weight-bold text-primary mb-3">Orders</h6>
                            <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Order Id#</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                </tr>
                                </thead>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </Col>
        </Row>
    </Container>
  );
};

export default AdminDashboard;
