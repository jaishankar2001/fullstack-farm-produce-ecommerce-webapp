import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import DropzoneComponent from "../../components/DropzoneComponent";
import api from "../../api/index";
import Dropdown from "../../components/Dropdown";


function OrderHistory() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        api.order.orderHistory()
        .then(response => {
            setOrders(response);
        })
        .catch(error => {
          console.error("Error fetching categories:", error);
        });
      }, []);

  return (
    <div className="container py-3">
    <h4 className="fw-bold py-1 mb-0 row mt-2 ml-3 mb-4">Order History</h4>
      <div class="table-responsive">
                    <table class="table">
                        <thead>
                          <tr>
                          <th scope="col">Order Id#</th>
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                          </tr>
                        </thead>
                        <tbody>
                            {orders.map((order, index) => {
                                return (
                                    <>
                                     <tr>
                            <td>
                                    <p class="mb-0 mt-4 d-flex text-center">{index + 1}</p>
                                </td>
                                <th scope="row">
                                    <div class="d-flex align-items-center">
                                        <img src={order?.images[0]?.img_url} class="img-fluid me-5 rounded-circle" style={{width: "80px", height: "80px"}} alt=""/>
                                    </div>
                                </th>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center">{order.productName}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center">$ {order.product.price}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center">{order.orderValue/order.product.price}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center"> $ {order.orderValue}</p>
                                </td>
                            </tr>

                                    </>

                                )
                            })}
                           
                        </tbody>
                    </table>
                </div>
                </div>
  );
}

export default OrderHistory;
