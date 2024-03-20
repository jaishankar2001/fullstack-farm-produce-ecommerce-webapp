import { useState, useEffect } from "react";
import moment from "moment";
import "react-toastify/dist/ReactToastify.css";
import api from "../../api/index";

function SubscriptionHistory() {
    const [subscription, setSubscription] = useState([]);

    useEffect(() => {
        api.subscription.subscriptionHistory()
        .then(response => {
            setSubscription(response);
        })
        .catch(error => {
          console.error("Error fetching Subscriptions:", error);
        });
      }, []);

  return (
    <div className="container py-3">
    <h4 className="fw-bold py-1 mb-0 row mt-2 ml-3 mb-4">My Subscriptions</h4>
      <div class="table-responsive">
                    <table class="table">
                        <thead>
                          <tr>
                          <th scope="col">Subscription Id#</th>
                          <th scope="col">Subscription Date</th>
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Type</th>
                          </tr>
                        </thead>
                        <tbody>
                            {subscription.map((item, index) => {
                                return (
                                    <>
                                     <tr>
                            <td>
                                    <p class="mb-0 mt-4 d-flex text-center">{index + 1}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex text-center"> {moment(item.orderDate).format(
                                        "MM/DD/YYYY"
                                      )}</p>
                                </td>
                                <th scope="row">
                                    <div class="d-flex align-items-center">
                                        <img src={item?.images[0]?.img_url} class="img-fluid me-5 rounded-circle" style={{width: "80px", height: "80px"}} alt=""/>
                                    </div>
                                </th>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center">{item.productName}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center">$ {item.product.price}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4 d-flex align-items-center"> $ {item.orderValue}</p>
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

export default SubscriptionHistory;
