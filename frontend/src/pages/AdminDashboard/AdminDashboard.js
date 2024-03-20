import 'chart.js/auto';
import React, { useRef } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { Bar } from "react-chartjs-2";

const AdminDashboard = () => {
// 
const ref = useRef();
const data = {
    labels: ['Dec','Jan', 'Feb', 'Mar'],
    datasets: [
      {
        label: 'Sales',
        data: [33, 53, 85, 41],
        fill: true,
        backgroundColor: '#2e5d3f',
        borderColor: '#2e5d3f',
      }
    ],
  };
const data1 = {
    labels: ['Dec','Jan', 'Feb', 'Mar'],
    datasets: [
      {
        label: 'Sales',
        data: [62, 29, 45, 86],
        fill: true,
        backgroundColor: '#2e5d3f',
        borderColor: '#2e5d3f',
      }
    ],
  };
const options = {
    scales: {
        xAxes: [{
            barPercentage: 0.4
        }]
    }
}
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
                          <i class="fas fa-download fa-sm text-white-50"></i> Run schedule for subscription</a>
                    </div>

                <div class="row">
                    <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-1">
                                <div class="hoverable">
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
                            <div class="card col-xl-5 col-md-6 shadow m-3 mb-4">
                                <div class="card-header py-3 mb-2">
                                    <h6 class="m-0 font-weight-bold text-primary">Order Sales</h6>
                                    <Bar ref={ref} data={data} options={options} />
                                </div>
                            </div>

                            <div class="card col-xl-5 col-md-6 shadow m-3 mb-4">
                                <div class="card-header py-3 mb-2">
                                    <h6 class="m-0 font-weight-bold text-primary">Subscription Sales</h6>
                                    <Bar ref={ref} data={data1} options={options} />
                                </div>
                            </div>
                    </div>

                    <div class="row">
                    <div class="card col-xl-5 shadow m-3 mb-4 ">
                        <div class="card-header py-3 mb-2">
                            <h6 class="m-0 font-weight-bold text-primary mb-3">List of Users</h6>
                            <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                <th scope="col">User ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email ID</th>
                                <th scope="col">Balance</th>
                                <th scope="col">Role</th>
                                </tr>
                                </thead>
                                <tbody>
                                <td>01</td>
                                <td>Drashti</td>
                                <td>drashti@dal.ca</td>
                                <td>100</td>
                                <td>Customer</td>
                                </tbody>
                                <tbody>
                                <td>02</td>
                                <td>Nikita</td>
                                <td>Nikita@dal.ca</td>
                                <td>150</td>
                                <td>Farmer</td>
                                </tbody>
                                <tbody>
                                <td>03</td>
                                <td>Tanuj</td>
                                <td>Tanuj@dal.ca</td>
                                <td>200</td>
                                <td>Customer</td>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                    <div class="card col-xl-6 shadow m-3 mb-4">
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
                                <tbody>
                                <td>01</td>
                                <td>Wonderland</td>
                                <td>Nikita</td>
                                <td>Nikita@dal.ca</td>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="card col-xl-12 shadow m-2 mb-4">
                        <div class="card-header py-2 mb-2">
                            <h6 class="m-0 font-weight-bold text-primary mb-3">Orders</h6>
                            <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Order Id#</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Payment Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <td>01</td>
                                    <td>03/16/2024</td>
                                    <td>Spinach</td>
                                    <td>5$</td>
                                    <td>3 lb</td>
                                    <td>15$</td>
                                    <td>Success</td>
                                </tbody>
                                <tbody>
                                    <td>02</td>
                                    <td>03/03/2024</td>
                                    <td>Milk</td>
                                    <td>8$</td>
                                    <td>2 litre</td>
                                    <td>16$</td>
                                    <td>Success</td>
                                </tbody>
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
