
import PrivateRoute from "./PrivateRoute/index"
import {Routes, Route } from 'react-router-dom';

import AdminDashboard from './AdminDashboard/index'
import PlaceForm from './AdminDashboard/Places/index'

import AllPlaces from './AdminDashboard/Places/AllPlaces'
import {useEffect,useState } from "react";
import { jwtDecode } from "jwt-decode";
import Login from "./Login/index";
import { useLocalState } from "./util/UseLocalStorage";
import {useUser} from "./UserProvider/index";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css"
import "./index.css";
import "./HomePage.css";


const App = () => {  
      const user=useUser();
      //const [jwt,setJwt]=useLocalState("","jwt");
   const getRoleFromJWT = () => {
   if(user.jwt){
    const decoded_jwt = jwtDecode(user.jwt);
   return decoded_jwt.authorities;
   }
        return [];
}
     const  [roles,setRoles]=useState(getRoleFromJWT());
    
   return (
      <div className="App">
        <Routes>
         
          <Route path="/" element={<Login/>}/>
         
          <Route path="/dashboard" element={
          
          roles.find((role)=>role==="ROLE_ADMIN")?
          (
           <PrivateRoute>
           <AdminDashboard/>
           </PrivateRoute>
           )
           :(
           <PrivateRoute>
           </PrivateRoute>
           )
          
          }/>
         
          
        
        
            
          <Route path="/add_place" element={
          roles.find((role)=>role==="ROLE_ADMIN")?
          ( 
          <PrivateRoute>
          <PlaceForm/>
          </PrivateRoute>
          )
          :
          (<PrivateRoute>
          </PrivateRoute>
          )
          }
          />
          
          <Route path="/all_places" element={
          roles.find((role)=>role==="ROLE_ADMIN")?
          ( 
          <PrivateRoute>
          <AllPlaces/>
          </PrivateRoute>
          )
          :
          (<PrivateRoute>
          <AllPlaces/>
          </PrivateRoute>
          )
          }
          />
        
      
          
        </Routes>
      </div>
   );
};

export default App;

