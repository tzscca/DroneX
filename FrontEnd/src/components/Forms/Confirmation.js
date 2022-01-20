import React from "react";
import { Row, Col, Button } from 'antd';
import '../../css/Order.css';

export default class Confirmation extends React.Component {

  //Go backward by one page (to 2) if you click back
  flipBackwards = () => {
    this.props.incrementPage(-1);
  }

  //Complete form and set up Order.js to send to backend.
  onClick = () => {
    this.props.completeForm();
  }

  //Form info passed through this.props.order by Order.js 
  //Display to user so they can check the info is correct. 
  //If not they can go back to the 0th or 1st page to edit the info.
  render() {
    console.log('props', this.props)
    const {
      month, dayOfMonth, hour, minute, fromAddress, toAddress, receivingPhone,
      date
    } = this.props.order
    const {
      droneOrRobot
    } = this.props
    return (
      <div style={{ left: '-10vw', position: 'relative' }}>
        <h2>
          Please confirm your order
        </h2>

        {/* Rows to place info label and info side by side. */}
        <Row justify="space-between">
          <Col>
            <h2>
              From
            </h2>
          </Col>
          <Col>
            <h4 style={{ fontWeight: '200' }}>
              {fromAddress}
            </h4>
          </Col>
        </Row>
        <Row justify="space-between">
          <Col>
            <h2>
              To
            </h2>
          </Col>
          <Col>
            <h4 style={{ fontWeight: '200' }}>
              {toAddress}
            </h4>
          </Col>
        </Row>
        <Row justify="space-between">
          <Col>
            <h2>
              By
            </h2>
          </Col>
          <Col>
            <h4 style={{ fontWeight: '200' }}>
              {
                (droneOrRobot === "drone" ? "Drone" : "Robot")
              }
            </h4>
          </Col>
        </Row>
        <Row justify="space-between">
          <Col>
            <h2>
              User Preferred Delivery Time
            </h2>
          </Col>
          <Col style={{ position: 'relative', top: '0.5vh' }}>
            {month}/{dayOfMonth} {hour}:{minute}
          </Col>
        </Row>
        <Row justify="space-between">
          <Col>
            <h2>
              Fee
            </h2>
          </Col >
          <Col>
            {
              (droneOrRobot === "drone" ? "$30" : "$20")
            }
          </Col>
        </Row>
        <Row justify="space-between">
          <Col style={{ marginRight: '10vw' }}>
            <h2>
              Payment Method
            </h2>
          </Col>
          <Col>
            <h2>
              Credit Card
            </h2>
          </Col>
        </Row>
        <div style={{ position: 'relative', marginLeft: '5vw', top: '3vh' }}>
          <Button style={{ width: 'fit-content' }} onClick={this.flipBackwards}>
            Previous
          </Button>
          <Button type="primary" onClick={this.onClick} style={{ width: 'fit-content', marginLeft: '3vw' }}>Submit Order</Button>
        </div>
      </div>
    );
  }
}