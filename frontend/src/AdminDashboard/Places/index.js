import React, { useState } from 'react';
import Header from "../header/Header";
import ajax from "../../services/fetchService"
import "../../styles/Registration.css";
import {useUser} from "../../UserProvider/index"
import { Button,Dropdown,DropdownButton,ButtonGroup,Row,Col} from 'react-bootstrap';
const PlaceForm = () => {
  const user=useUser();
  const [place, setPlace] = useState({
    area: '',
    address: '',
    price: '',
    status: '',
    imageUrl: '', // This will be set after image upload
  });
  const [image, setImage] = useState(null);

  // Handle form inputs
  const handleChange = (e) => {
    setPlace({
      ...place,
      [e.target.name]: e.target.value,
    });
  };

  // Handle image file selection
  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

const handleImageUpload = () => {
  const formData = new FormData();
  formData.append('file', image);

  return fetch('/api/uploadImage', {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${user.jwt}`, // Set the JWT token if needed
    },
    body: formData, // Important: Don't manually set Content-Type here
  })
    .then(response => response.text())
    .then((data) => {
      console.log(data); 
      return data; // Assuming this is the image URL
    })
    .catch((e) => {
      console.error(e);
      return null; 
    });
};




  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    const imageUrl = await handleImageUpload();
    if (imageUrl) {
      const updatedPlace = { ...place, imageUrl };
      ajax('/api/places','POST',user.jwt,updatedPlace)
      .then((data)=>alert("palace saved sucessfuly."))
      .catch((err)=>console.log(err));
    }else {
      alert('Image upload failed. Please try again.');
    }
  };

  return (
  <>
  <Header/>
   <form onSubmit={handleSubmit}>
    <div className="mt-5">
      <div className="row justify-content-center">
     <div className="col-md-8 col-lg-6 detail">
        <div className="mb-3">
       <label className="form-label labelM">Area</label>
      <input
        type="text"
        name="area"
         className="form-control inputM"
        placeholder="Area"
        value={place.area}
        onChange={handleChange}
        required
      />
      </div>
      <div className="mb-3">
         <label className="form-label labelM">Address</label>
      <input
        type="text"
         className="form-control inputM"
        name="address"
        placeholder="Address"
        value={place.address}
        onChange={handleChange}
        required
      />
      </div>
      <div className="mb-3">
       <label className="form-label labelM">Price</label>
      <input
        type="number"
         className="form-control inputM"
        name="price"
        placeholder="Price"
        value={place.price}
        onChange={handleChange}
        required
      />
      </div>
     <div className="mb-3">
      <label className="form-label labelM">Image</label>
      <input
        type="file"
        className="form-control inputM"
        name="image"
        onChange={handleImageChange}
        required
      />
      </div>
      <Button 
      type="submit"
                      variant="success"
                      className="me-5"
                      size="lg">Add Place</Button>
    </div>
    </div>
    </div>
    </form>
    </>
  );
};

export default PlaceForm;

