import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

const SubscriptionModal = ({
  isOpen,
  onClose,
  onConfirm,
  product,
  productId,
}) => {
  const [subscription, setSubscription] = useState("weekday");
  const [customDays, setCustomDays] = useState([]);

  const handleSubscriptionChange = (event) => {
    setSubscription(event.target.value);
    if (event.target.value !== "custom") {
      setCustomDays([]);
    }
  };

  const handleDayToggle = (dayAbbreviation) => {
    setCustomDays(prevDays =>
      prevDays.includes(dayAbbreviation)
        ? prevDays.filter(d => d !== dayAbbreviation)
        : [...prevDays, dayAbbreviation]
    );
  };
  const handleSubmit = () => {
    let payload = {
      product_id: productId,
      farm_id: product?.farm?.id,
    };

    if (subscription === "custom") {
      // Initialize all days with 0
      const daysPayload = {
        mon: 0,
        tue: 0,
        wed: 0,
        thu: 0,
        fri: 0,
        sat: 0,
        sun: 0,
      };

      // Update selected days to 1
      customDays.forEach((day) => {
        daysPayload[day.toLowerCase()] = 1;
      });

      payload = {
        ...payload,
        name: "CUSTOM",
        ...daysPayload,
      };
    } else {
      payload = {
        ...payload,
        name: subscription.toUpperCase(),
      };
    }

    onConfirm(payload);
    onClose();
  };

  const formCheckStyle = {
    padding: "0.5rem",
    margin: "0.5rem 0",
    border: "1px solid #dee2e6",
    borderRadius: "0.25rem",
  };

  const customCheckboxStyle = {
    backgroundColor: "#f8f9fa",
    borderColor: "#ced4da",
    pointerEvents: subscription === "custom" ? "auto" : "none",
    opacity: subscription === "custom" ? 1 : 0.6,
  };

  const modalStyle = {
    padding: "2rem",
    borderRadius: "0.5rem",
    boxShadow: "0 0.5rem 1rem rgba(0, 0, 0, 0.1)",
  };

  const buttonStyle = {
    fontWeight: "bold",
    boxShadow: "none",
  };

  const cancelBtnStyle = {
    ...buttonStyle,
    backgroundColor: "transparent",
    color: "#6c757d",
    boxShadow: "none",
  };

  return (
    <Modal show={isOpen} onHide={onClose} centered style={modalStyle}>
      <Modal.Header
        closeButton
        style={{ borderBottom: "0 none", paddingBottom: "0" }}
      >
        <Modal.Title
          style={{ fontFamily: "Arial, sans-serif", fontWeight: "bold" }}
        >
          Choose Your Subscription Plan
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <div style={formCheckStyle}>
            <Form.Check
              type="radio"
              id="weekday"
              label="Weekday"
              name="subscriptionOptions"
              value="weekday"
              checked={subscription === "weekday"}
              onChange={handleSubscriptionChange}
            />
          </div>
          <div style={formCheckStyle}>
            <Form.Check
              type="radio"
              id="weekend"
              label="Weekend"
              name="subscriptionOptions"
              value="weekend"
              checked={subscription === "weekend"}
              onChange={handleSubscriptionChange}
            />
          </div>
          <div style={formCheckStyle}>
            <Form.Check
              type="radio"
              id="custom"
              label="Custom"
              name="subscriptionOptions"
              value="custom"
              checked={subscription === "custom"}
              onChange={handleSubscriptionChange}
            />
          </div>
          {subscription === "custom" && (
            <div style={{ ...customCheckboxStyle, ...formCheckStyle }}>
              {[
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday",
              ].map((day, index) => {
                const dayAbbreviations = [
                  "mon",
                  "tue",
                  "wed",
                  "thu",
                  "fri",
                  "sat",
                  "sun",
                ];
                return (
                  <Form.Check
                    key={day}
                    type="checkbox"
                    label={day}
                    checked={customDays.includes(dayAbbreviations[index])}
                    onChange={() => handleDayToggle(dayAbbreviations[index])}
                  />
                );
              })}
            </div>
          )}
        </Form>
      </Modal.Body>
      <Modal.Footer style={{ borderTop: "0 none" }}>
        <Button
          variant="outline-primary"
          onClick={onClose}
          style={cancelBtnStyle}
        >
          Cancel
        </Button>
        <Button variant="primary" onClick={handleSubmit}>
          Confirm Subscription
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default SubscriptionModal;
