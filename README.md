<img src="./assets/logo.png" alt="Eocpick Logo" width="100" height="100">

# ECOPICK

</div>

## Summary

Our website serves as a platform connecting farmers and consumers, facilitating seamless transactions of fresh farm products. With user-friendly registration processes for both farmers and consumers, individuals can easily join our community. Consumers gain access to a wide variety of farm-fresh products available for purchase, while also having the option to subscribe to their favorite items for regular delivery. Meanwhile, farmers can showcase their farms and products on the platform, expanding their reach and directly connecting with their customers. By fostering direct relationships between farmers and consumers, our website promotes transparency, supports local agriculture, and empowers individuals to make informed choices about their food purchases.

## Tools & Tech Stacks used : -

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-green)](https://spring.io/projects/spring-boot)
[![React.js](https://img.shields.io/badge/React.js-16.x-purple)](https://reactjs.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-4.x-red)](https://getbootstrap.com/)
[![MySQL](https://img.shields.io/badge/MySQL-v8.0-yellow)](https://www.mysql.com/)
[![stripe](https://img.shields.io/badge/Stripe-v22.7-blue)](https://stripe.com/en-ca)
[![map](https://img.shields.io/badge/GoogleMaps-v2.19.2-brown)](https://developers.google.com/maps)

# Ecopick backend Setup

## Prerequisites

- Java `v17.0.2`
- JDK `v17`
- Maven `v3.9.6`

## Getting Started

Follow these steps to set up and run the project locally.

### 1. Clone the Repository

```bash
 git clone git@git.cs.dal.ca:courses/2024-winter/csci5308/Group09.git
 OR
 git clone https://git.cs.dal.ca/courses/2024-winter/csci5308/Group09.git
```

### 2. Navigate to the project directory

```bash
cd Group09/backend
```

### 3. Build the project

- Run the following command to install backend dependencies:

```bash
mvn clean install
```

### 5. Run the application

- After installing dependencies, start the Spring Boot backend server by running:

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## Backend Dependencies

| Dependency Group         | Description                                               | Artifact                       | Version  |
| ------------------------ | --------------------------------------------------------- | ------------------------------ | -------- |
| org.springframework.boot | Starter for using Spring Boot's Actuator endpoints        | spring-boot-starter-actuator   |          |
| org.springframework.boot | Starter for using Spring Data JPA                         | spring-boot-starter-data-jpa   |          |
| org.springframework.boot | Starter for building web, including RESTful, applications | spring-boot-starter-web        |          |
| org.apache.commons       | Apache Commons Lang library                               | commons-lang3                  |          |
| org.mockito              | Mocking framework for unit tests                          | mockito-core                   | 3.9.0    |
| com.mysql                | MySQL JDBC driver                                         | mysql-connector-j              |          |
| org.projectlombok        | Lombok library for reducing boilerplate code              | lombok                         |          |
| org.springframework.boot | Starter for testing Spring Boot applications              | spring-boot-starter-test       |          |
| org.springframework.boot | Starter for using Spring Boot's validation support        | spring-boot-starter-validation |          |
| org.springframework.boot | Starter for using Spring Security                         | spring-boot-starter-security   |          |
| io.jsonwebtoken          | Java JWT: JSON Web Token for Java                         | jjwt-api                       | 0.11.2   |
| io.jsonwebtoken          | Implementation of Java JWT                                | jjwt-impl                      | 0.11.2   |
| io.jsonwebtoken          | Jackson support for Java JWT                              | jjwt-jackson                   | 0.11.2   |
| com.amazonaws            | AWS SDK for Amazon S3                                     | aws-java-sdk-s3                | 1.12.433 |
| org.springframework.boot | Starter for using Spring Boot's email support             | spring-boot-starter-mail       |          |
| org.modelmapper          | Object mapping library                                    | modelmapper                    | 3.1.1    |
| com.stripe               | Stripe API for Java                                       | stripe-java                    | 22.7.0   |
| junit                    | JUnit testing framework for Java                          | junit                          | 4.13.2   |

# Ecopick frontend Setup

## Prerequisites

- [NodeJS](https://nodejs.org/en) `v20.x`
- [npm](https://www.npmjs.com/) `v6.x`

## Getting Started

Follow these steps to set up and run the project locally.

### 1. Clone the Repository

```bash
 git clone git@git.cs.dal.ca:courses/2024-winter/csci5308/Group09.git
 OR
 git clone https://git.cs.dal.ca/courses/2024-winter/csci5308/Group09.git
```

### 2. Navigate to the project directory

```bash
cd Group09/frontend
```

### 3. Install dependencies

- Run the following command to install frontend dependencies:

```bash
npm install
```

### 4. start the development

- After installing dependencies, start the React.js development server by running:

```bash
npm run start
```

- Open your web browser and go to [http://localhost:3000](http://localhost:3000) to access the website.

## Frontend Dependencies

| Dependency                          | Version | Description                                                                     | Installation Command                              |
| ----------------------------------- | ------- | ------------------------------------------------------------------------------- | ------------------------------------------------- |
| @fortawesome/fontawesome-svg-core   | ^6.5.1  | SVG icon library core for Font Awesome.                                         | `npm install @fortawesome/fontawesome-svg-core`   |
| @fortawesome/free-brands-svg-icons  | ^6.5.1  | Free SVG icons for brands from Font Awesome.                                    | `npm install @fortawesome/free-brands-svg-icons`  |
| @fortawesome/free-regular-svg-icons | ^6.5.1  | Free SVG icons in regular style from Font Awesome.                              | `npm install @fortawesome/free-regular-svg-icons` |
| @fortawesome/free-solid-svg-icons   | ^6.5.1  | Free SVG icons in solid style from Font Awesome.                                | `npm install @fortawesome/free-solid-svg-icons`   |
| @fortawesome/react-fontawesome      | ^0.2.0  | React component for Font Awesome icons.                                         | `npm install @fortawesome/react-fontawesome`      |
| @react-google-maps/api              | ^2.19.2 | React components for Google Maps API integration.                               | `npm install @react-google-maps/api`              |
| @stripe/stripe-js                   | ^2.4.0  | JavaScript library for Stripe payment processing.                               | `npm install @stripe/stripe-js`                   |
| @testing-library/jest-dom           | ^5.17.0 | Testing utilities for Jest with DOM testing.                                    | `npm install @testing-library/jest-dom`           |
| @testing-library/react              | ^13.4.0 | Testing utilities for React components.                                         | `npm install @testing-library/react`              |
| @testing-library/user-event         | ^13.5.0 | Testing utilities for simulating user events in testing.                        | `npm install @testing-library/user-event`         |
| axios                               | ^1.6.7  | Promise-based HTTP client for browser and Node.js.                              | `npm install axios`                               |
| bootstrap                           | ^5.3.2  | Front-end framework for building responsive and mobile-first websites.          | `npm install bootstrap`                           |
| chart.js                            | ^4.4.2  | JavaScript library for interactive charts and graphs.                           | `npm install chart.js`                            |
| moment                              | ^2.30.1 | JavaScript library for parsing, validating, manipulating, and formatting dates. | `npm install moment`                              |
| react                               | ^18.2.0 | JavaScript library for building user interfaces.                                | `npm install react`                               |
| react-bootstrap                     | ^2.10.1 | React components for Bootstrap framework.                                       | `npm install react-bootstrap`                     |
| react-chartjs-2                     | ^5.2.0  | React components for Chart.js integration.                                      | `npm install react-chartjs-2`                     |
| react-dom                           | ^18.2.0 | React library for DOM rendering.                                                | `npm install react-dom`                           |
| react-dropzone                      | ^14.2.3 | React component for file uploads with drag-and-drop support.                    | `npm install react-dropzone`                      |
| react-google-autocomplete           | ^2.7.3  | React component for Google Places Autocomplete.                                 | `npm install react-google-autocomplete`           |
| react-modal                         | ^3.16.1 | Accessible modal dialog component for React.                                    | `npm install react-modal`                         |
| react-responsive-carousel           | ^3.2.23 | React component for responsive carousels.                                       | `npm install react-responsive-carousel`           |
| react-router-dom                    | ^6.22.0 | React router library for declarative routing.                                   | `npm install react-router-dom`                    |
| react-scripts                       | ^5.0.1  | Scripts and configuration used by Create React App.                             | `npm install react-scripts`                       |
| react-toastify                      | ^10.0.4 | React component for toast notifications.                                        | `npm install react-toastify`                      |
| recoil                              | ^0.7.7  | State management library for React applications.                                | `npm install recoil`                              |
| sass                                | ^1.70.0 | CSS preprocessor extension for writing structured stylesheets.                  | `npm install sass`                                |
| web-vitals                          | ^2.1.4  | Library for measuring web vital metrics in web pages.                           | `npm install web-vitals`                          |

# Deploy in VM

## Overview

Mainly there are 3 components - Backend (Spring Boot), Frontend (React) and Database (MySQL).

We have deployed our application on the virtual machine provided. For backend are using docker and for frontend we are using nginx as deployment server.

## Prerequisites

- VM with Ubuntu OS

- [nginx](https://www.nginx.com/) installed in VM

- Java installed in VM - check the version in prerequisites of development

- fill the appropriate values in [application.properties](./backend/src/main/resources/application.properties)

- fill the appropriate values in [.env](./frontend/.env)

### Frontend deployment steps

- Following is the nginx configuration in VM : -

```
server {
    listen 80 default_server;
	  listen [::]:80 default_server;
    access_log /var/log/nginx/app.log;
    root /var/www/build;
    index index.html index.htm;
    try_files $uri /index.html;
    location / {
        try_files $uri $uri/ = 404;
    }
}
```

- Start Nginx

```bash
sudo systemctl start nginx
```

- once you have done above configuration inside etc/nginx/sites-enabled folder, you will be able to access nginx index file.

- cd into frontend directory and run install command.

```bash
cd frontend
npm install
```

- We will run npm build command to get build package of react

```bash
npm build
```

- Once build folder is created we will move build the folder the Vm : /var/www/html, following is the command that we have used in our CI-CD pipeline to copy our build folder

```bash
scp -r -o StrictHostKeyChecking=no -i $ID_RSA frontend/build/* ${SERVER_USER}@${SERVER_IP}:/var/www/html/
```

- Once it has copied all of the build files, it will serve index.html on port :80 by default

### Backend deployment steps

- Go to backend folder

- run mvn package command to generate the jar file in target folder

- Build the docker container with the provided dockerfile, you can use the latest image

```bash
docker build -t docker.io/tanuj3920 ecopick-backend:$CI_COMMIT_SHORT_SHA -f ./Dockerfile .
```

- Push the docker container

```bash
docker push docker.io/tanuj3920/ecopick-backend:$CI_COMMIT_SHORT_SHA
```

- connect to the remote VM using SSH

- remove the docker container name : "ecopick-backend"

```bash
docker container rm -f ecopick-backend
```

- Run the docker container in VM : -

```bash
docker run -d -p 8080:8080 --name ecopick-backend docker.io/tanuj3920/ecopick-backend:$CI_COMMIT_SHORT_SHA
```

- Once the above command is run successfully, it will be accessible at port number 8080.

# Usage Scenario

### For Customers:

1. **Registration and Profile Setup:**

- Consumers can sign up for an account by providing basic information such as First name, Last name and Email ID.

2. **Exploring Farm and Farm-Products:**

- Upon successful registration, Customers can explore the farms listed on the platform.
- Consumers can also browse through a wide range of farm-fresh products listed on the platform
- They can view detailed product descriptions, including images, pricing, and farmer information.

3. **Purchasing Products:**

- Customer can select desired products, quantity and proceed to checkout.

4. **Subscription Service:**

- Customer interested in receiving regular deliveries of their favorite products can subscribe to them.
- They can customize subscription preferences such as delivery frequency (Weekdays, Weekends or Preferred days).
- They can view and update their subscription information as needed.

5. **Wallet Service:**

- Customer have access to a digital wallet service integrated into the platform.
- They can deposit funds into their wallet and use the funds while purchasing the products.
- They can also view thier wallet history which contains the of transactions performed by the user.

### For Farmers:

1. **Registration and Farm Setup:**

- Consumers can sign up for an account by providing basic information such as First name, Last name and Email.
- Upon successful registration, they can set up their farm profile by adding information such as name, descripton, images and Location.

2. **Listing Farm Products:**

- Farmers can add their farm products to the platform, providing detailed descriptions, images, pricing, and stock information.
- They can manage their product inventory and update listings as needed.
- They can view the list of subscriptions made on their products.

### For Admin:

1. **Admin Service**

- Upon successful login with Admin credentials, the admin can view the Admin Dashboard.
- On the Admin Dashboard page, they can review statistics, such as number of farms, number of products, total sales, etc.
- They can also track the montly Order sales and Subscription sales.

# Features

## Registration Page

- A new user has to fill all the necessary details on the registration page.
- Registered users can click on the 'Login' link to navigate to the Log in page.

<center>
<img src="./assets/SignUp.png" alt="Registration Page" width="600 px" height="550 px">
</center>

---

- After successful registration, a verification mail is sent to the registered Email address

<center>
<img src="./assets/registrationMail.png" alt="" width="450 px" height="300 px">
</center>

## Login Page

- Registered user can login to the website by entering the registered Email address and password on the Login page.
<center>
<img src="./assets/LogIn.png" alt="Login Page">
</center>

## Landing Page

- After successful login, users are redirected to the Home Page.
- Users can navigate directly to the Farms page by clicking on 'Farms' on the navigation bar.
- Users can navigate directly to the Products page by clicking on 'Products' on the navigation bar.
<center>
<img src="./assets/HomePage.png" alt="Landing Page">
</center>

---

## Banner Page

- From the banner page user will be redirected to the products section.
<center>
<img src="./assets/bannerPage.png" alt="Banner Page">
</center>

---

**User Dropdown**

- Users can navigate to different pages by clicking on the options provided in the User Dropdown menu.
<center>
<img src="./assets/userDropdown.png" alt="User Dropdown menu">
</center>

---

## Farms section

- On the Home page, In the Farms section, latest 8 farms are displayed.
- User can view the farm details by clicking on the 'Visit the farm' button.

<center>
<img src="./assets/farms.png" alt="Farms Section">
</center>

---

### Farm page for Farmer

- Farmers can view their farms by clicking on 'My Farms' from the dropdown list.
- Farmers can view specific farm details by clicking on 'View Farm' button.
- Farmers can add a new farm by clicking on the 'Add Farm' button

---

<center>
<img src="./assets/myFarms.png" alt="Farmer farm page" >
</center>

---

**Adding Farm**

- Once adding necessary details like Farm name, Farm description, Farm images and Farm address, farmer can click on 'Submit' button to add the farm
.
<center>
<img src="./assets/addFarm.png" alt="Add Farm" >
</center>

---

- Farmers can edit their farm details by clicking on the 'Edit Farm' button.
- Farmers can delete their farm by clicking on the 'Delete Farm' button.

<center>
<img src="./assets/farmerFarm.png" alt="Farmer farm page" >
</center>

---

**Edit Farm details**

- Once making changes to the farm details, farmer can click on 'Update' button to update the farm details.

<center>
<img src="./assets/editFarm.png" alt="Edit Farm page" >
</center>

---

**Delete Farm**

- Farmer can delete the farm by clicking on 'Delete' button.
<center>
<img src="./assets/deleteFarm.png" alt="Delete Farm page" >
</center>

---

### Farm page for Customer

- Customers can view the farm details by navigating to the Farms page from the title bar.

<center>
<img src="./assets/customerFarm.png" alt="Customer farm page" >
</center>

## Products section

- On the Home page, In the Products section, the latest 8 products are displayed.
- User can view the product details by clicking on the 'View Product' button.

<center>
<img src="./assets/products.png" alt="Product Section" >
</center>

---

### Product page for Farmer

- Farmers can view their farms by clicking on 'My Products' from the dropdown list.
- Farmers can view their farm details by clicking on 'View Product' button.
- Farmers can add new farm by clicking on the 'Add Product' button

---

<center>
<img src="./assets/myProducts.png" alt="Farmer farm page" >
</center>

---

- Farmers can edit the product details by clicking on the 'Edit Product' button.
- Farmers can delete the product by clicking on the 'Delete Product' button.

<center>
<img src="./assets/farmerProduct.png" alt="Farmer Product page" >
</center>

---

**Adding Product**

- Once adding necessary details like Product name, Product category, Product price, etc. farmer can click on 'Submit' button to add the product
.
<center>
<img src="./assets/addProduct.png" alt="Add Product" >
</center>

---

**Editing Product details**

- To make changes to the product details, farmer can click on 'Update' button to update the product details.
<center>
<img src="./assets/editProduct.png" alt="Edit Product page" >
</center>

---

**Deleting Product**

- Farmer can delete the product by clicking on 'Delete' button.
<center>
<img src="./assets/deleteProduct.png" alt="Delete Product page" >
</center>

---

### Product page for Customer

- Customer can select the quantity for the product they want to buy.
- Customers can buy the product by clicking on the 'Buy Now' button.
- Customers can subscribe to the product for recurring delivery by clicking on the 'Subscribe' button.

<center>
<img src="./assets/productPage.png" alt="Customer Product page" >
</center>

---

**Buying Product**

- Customers can buy the product by clicking on the 'Buy Now' button.

<center>
<img src="./assets/buyProduct.png" alt="Product Buying" >
</center>

---

**Subscribing Product**

- From the given options, customer can choose their subscription plan.
- Once chosen, they can confirm the subscription by clicking on the 'Confirm Subscription' button.

<center>
<img src="./assets/subscription.png" alt="Product Subscription" >
</center>

---

**Customizing the Subscription**

- Customers can customize their subscription as well.

<center>
<img src="./assets/customSubscription.png" alt="Product Subscription" >
</center>

## Subscription Data for Farmer

- Farmers can view the list of products which are subscribed by customers.

<center>
<img src="./assets/subscriptionFarmer.png" alt="Subscription Data for Farmer">
</center>

## Subscription Data for Customer

- Customer can view the the list of the products that they have subscribed to.

<center>
<img src="./assets/subscriptionHistory.png" alt="Subscription History" >
</center>

---

- Customer can also check which days they have subscribed for.

<center>
<img src="./assets/customSubscriptionData.png" alt="Subscription Data" >
</center>

## Banner section

- Customer can navigate to the Product section from the banner page.
<center>
<img src="./assets/bannerPage.png" alt="Subscription History" >
</center>

## Footer section

<center>
<img src="./assets/footer.png" alt="Footer Section" >
</center>

## Admin Dashboard

- Upon successful login using admin credentials, the admin is redirected to the Admin Dashboard page.
- On the Admin Dashboard page, admin can review statistics like, Total sales, Number of Users, Number of Products and Number of Farms.
- Admin can also view graph data for Order sales and Subscription sales of last four months.
- The CRON job will run automatically at 11:59 PM everyday. But if admin wants to place orders for the subscribed users for the next day manually, they can do it by clicking on 'Run Schedule for subscription'.
<center>
<img src="./assets/adminDashboard.png" alt="Admin Dashboard" >
</center>

---

- Admin can also view tabular data for list of Users, Farms, Products and orders.
<center>
<img src="./assets/table1.png" alt="Tabular Data" >
</center>

<center>
<img src="./assets/table2.png" alt="Tabular Data" >
</center>

<center>
<img src="./assets/table3.png" alt="Tabular Data" >
</center>

## Wallet

- Customers can add funds to their wallet.
- Customers can use these funds to make purchases.
- If a user has subscribed to a product, funds for the subscription will be deducted from the wallet on the day before the delivery.

<center>
<img src="./assets/wallet.png" alt="Wallet Page" >
</center>

## Wallet History

- Customer can track their transactions by navigating to the 'Wallet History' page from the User dropdown menu.

<center>
<img src="./assets/walletHistory.png" alt="Wallet History Page" >
</center>

## Order History

- Customers can view the list of products they have purchased by navigating to the 'Order History' page from the User dropdown menu.
<center>
<img src="./assets/orderHistory.png" alt="Order History Page" >
</center>

## Credits

### Developer Team

| Name                     | Email              |
| ------------------------ | ------------------ |
| TANUJ VIPULKUMAR DOSHI   | tanuj.doshi@dal.ca |
| NIKITA DAVIES            | nk548914@dal.ca    |
| DRASHTI VIJAYKUMAR PATEL | dr954742@dal.ca    |
| KUNJ HITESHKUMAR PATHAK  | kn743706@dal.ca    |
| JAISHANKAR MOHANRAJ      | js830845@dal.ca    |

### Client Team

| Name            | Email           |
| --------------- | --------------- |
| KHUSH PATEL     | kh472243@dal.ca |
| VYANSI DIYORA   | vy744910@dal.ca |
| PIYUSH JOSHI    | py287300@dal.ca |
| RIDDHO BHADRA   | rd588330@dal.ca |
| ABHISHEK KAPOOR | ab210637@dal.ca |

---

# ✅CI/CD

## Build

- In our project, we also have implemented a similar approach for the build stage of our CI Pipeline. We have two distinct jobs:the frontend build job and the backend build job.

<center>
<img src="./assets/CICD1.jpg" alt="Build Stage" >
</center>

## Test

- To test our application, we have used JUnit, a popular open-source testing framework for Java. We have integrated JUnit tests into our CI pipeline by running the "mvn test" command, which invokes the Maven build tool to execute the tests.

<center>
<img src="./assets/CICD2.jpg" alt="Test Stage" >
</center>

## Code Quality

- The Continuous Integration (CI) Pipeline of the application includes a stage for code quality assurance, which covers both the Frontend and Backend code.

1. **Job1:** The frontend code quality is ensured by integrating prettier into the pipeline, which checks for proper formatting of the codebase.
2. **Job2:** The backend code quality is evaluated by running designated code smell tools, which generate reports of potential issues in the code. These code smell reports are then saved in artifacts, which can be downloaded later to analyze the code in detail.

<center>
<img src="./assets/CICD3.jpg" alt="Test Stage" >
</center>

## Publish

- In publish stage, we are publishing the Backend with Docker, by building a Docker image and pushing it to Docker.
- Successful run of publish stage will result in available docker image in docker hub.

<center>
<img src="./assets/CICD4.jpg" alt="Test Stage" >
</center>

## Deploy

- In Deploy stage we are deploying Frontend and Backend.

1. **Job 1 (Backend) :** - Using SSH we are logging in to VM and pulling the docker image, the docker image is run on port number 8080
2. **Job 2 (Frontend) :** - Using SSH we are transferring current artifact files of the build folder to /var/www/html in the VM, resulting frontend index file is run on default port : 80.

<center>
<img src="./assets/CICD5.jpg" alt="Test Stage" >
</center>

## ✅Test

### ▪️ Coverage

Jacoco is used to show code coverage of the test cases. The project's service layer has 75% Line Coverage.

### ️▪️ Integration tests

We have followed best practices for mocking the dependent classes. System under test is beign tested in isolation.

### ▪️ TDD adherence

Some of our APIs are developed by following Test Driven Developement approach.
