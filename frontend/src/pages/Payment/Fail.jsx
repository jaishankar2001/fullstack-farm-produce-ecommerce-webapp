import { EmailVerification } from "../../assets/images/index";

function Fail() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center align-items-center">
        <div className="col-md-6">
          <div className="text-center mb-4">
            <img
              src={EmailVerification}
              alt="Email Verification"
              className="img-fluid"
              style={{ maxWidth: "70%", height: "auto" }}
            />
          </div>
          <div className="alert alert-warning text-center" role="alert">
            Fail
          </div>
        </div>
      </div>
    </div>
  );
}

export default Fail;
