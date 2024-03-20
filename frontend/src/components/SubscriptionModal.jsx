import React, { useState } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';

const SubscriptionModal = ({ isOpen, onClose, onConfirm, product, productId }) => {
  const [subscription, setSubscription] = useState('weekdays');
  const [customDays, setCustomDays] = useState([]);

  const handleSubscriptionChange = (event) => {
    setSubscription(event.target.value);
    if (event.target.value !== 'custom') {
      setCustomDays([]);
    }
  };

  const handleDayToggle = (day) => {
    setCustomDays((prevDays) =>
      prevDays.includes(day) ? prevDays.filter(d => d !== day) : [...prevDays, day]
    );
  };
  const handleSubmit = () => {
    let payload = {
      product_id: productId,
      farm_id: product?.farm?.id,
    };
  
    if (subscription === 'custom') {
      // Initialize all days with 0
      const daysPayload = {
        name: 'CUSTOM',
        monday: 0, tuesday: 0, wednesday: 0, thursday: 0, friday: 0, saturday: 0, sunday: 0
      };
  
      // Update selected days to 1
      customDays.forEach(day => {
        daysPayload[day.toLowerCase()] = 1;
      });
  
      payload = {
        ...payload,
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

  return (
    <Modal show={isOpen} onHide={onClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Choose Your Subscription Plan</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Check 
            type="radio"
            id="weekdays"
            label="Weekdays"
            name="subscriptionOptions"
            value="weekdays"
            checked={subscription === 'weekdays'}
            onChange={handleSubscriptionChange}
          />
          <Form.Check 
            type="radio"
            id="weekends"
            label="Weekends"
            name="subscriptionOptions"
            value="weekends"
            checked={subscription === 'weekends'}
            onChange={handleSubscriptionChange}
          />
          <Form.Check 
            type="radio"
            id="custom"
            label="Custom"
            name="subscriptionOptions"
            value="custom"
            checked={subscription === 'custom'}
            onChange={handleSubscriptionChange}
          />
          {subscription === 'custom' && (
            <div>
              {['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'].map((day) => (
                <Form.Check 
                  key={day}
                  type="checkbox"
                  label={day}
                  checked={customDays.includes(day)}
                  onChange={() => handleDayToggle(day)}
                />
              ))}
            </div>
          )}
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
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
