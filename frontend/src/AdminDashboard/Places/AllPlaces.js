import Header from '../header/Header';
import { Button, Card, Row, Col } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import { useUser } from "../../UserProvider/index";
import { useState, useEffect } from 'react';
import ajax from '../../services/fetchService';
import StatusBadge from "../../StatusBadge/index";
import styles from "../../styles/Card.module.scss";
import "../../styles/Registration.css";

const AllPlaces = () => {
  const user = useUser();
  const [places, setPlaces] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    ajax('/api/places', 'GET', user.jwt)
      .then((data) => {
        setPlaces(data);
        console.log(data);
      });
  }, [user.jwt]);

const handleDelete = (placeId) => {
  if (window.confirm("Are you sure you want to delete this place?")) {
    ajax(`/api/places/${placeId}`, 'DELETE', user.jwt) // Pass the placeId as a URL parameter
      .then(() => {
        // Remove the deleted place from the state
        setPlaces(prevPlaces => prevPlaces.filter(place => place.id !== placeId));
        alert("Place deleted successfully.");
      })
      .catch(error => {
        console.error("Error deleting place:", error);
        alert("Failed to delete the place.");
      });
  }
};


  const renderPlaceCards = (status) => {
    const filteredPlaces = places.filter((place) => place.status === status);

    if (filteredPlaces.length === 0) {
      return <div style={{ marginLeft: "5%", marginTop: "3%" }}>There are no {status} Places</div>;
    }

    return (
      <div className={`d-grid gap-5 ${styles.cards}`} style={{ gridTemplateColumns: "repeat(auto-fit,50rem)", marginBottom: "5%" }}>
        {filteredPlaces.map((place) => (
          <Card key={place.id} style={{ width: "50rem", height: "50rem" }} className={`${styles.card}`}>
            <Card.Img
              variant="top"
              src={`http://localhost:8080${place.imageUrl}`}
              alt="Place image"
              style={{ maxHeight: '30rem', objectFit: 'cover' }}
            />
            <Card.Body className="d-flex flex-column justify-content-around">
              <Card.Title className={`${styles.card_title}`}>Address: {place.address}</Card.Title>
              <div className="d-flex align-items-start">
                <StatusBadge text={place.status}></StatusBadge>
              </div>
              <Card.Text style={{ marginTop: "1em" }} className={`${styles.card_text}`}>
                <p><b>Area</b>: {place.area}</p>
                <p><b>Price</b>: {place.price}</p>
              </Card.Text>
              <Row className="btns">
                <Col>
                  <Button className={`${styles.btn}`} variant="primary" onClick={() => navigate(`/edit/${place.id}`)}>
                    Edit
                  </Button>
                </Col>
                <Col>
                  <Button className={`${styles.btn}`} variant="danger" onClick={() => handleDelete(place.id)}>
                    Delete
                  </Button>
                </Col>
              </Row>
            </Card.Body>
          </Card>
        ))}
      </div>
    );
  };

  return (
    <div style={{ marginBottom: "5%" }}>
      <Header />
      <h1 style={{ paddingLeft: "40%", paddingTop: "5%", fontSize: "4rem" }}>List of Places</h1>
      <div className="response-wrapper submitted">
        <div className="h3 px-2 response-wrapper-title" style={{ marginLeft: "5%", marginTop: "3%" }}>Pending</div>
        {renderPlaceCards("Pending")}

        <div className="h3 px-2 response-wrapper-title" style={{ marginLeft: "5%", marginTop: "3%" }}>Sold</div>
        {renderPlaceCards("Sold")}
      </div>
    </div>
  );
};

export default AllPlaces;

