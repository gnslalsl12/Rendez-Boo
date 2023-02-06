import React from 'react'
import { Route, Routes, useLocation } from 'react-router-dom'
import { Home, Rendezboo, Signal, Login, Rocket, Docking1, Join } from './pages'
import Navbar from './components/Navbar'
import './App.css'
import Userinfo from './pages/Userinfo'
import Inventory from './pages/Inventory'
function App() {
  const location = useLocation()

  return (
    <div className="App">
      <div>
        {!(
          location.pathname === '/home' ||
          location.pathname === '/join' ||
          location.pathname === '/login' ||
          location.pathname === '/Login'
        ) && <Navbar />}
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route exact path="/" element={<Rendezboo />} />
          <Route exact path="/signal" element={<Signal />} />
          <Route path="/signal/:userid" element={<Signal />} />
          <Route path="/login" element={<Login />} />
          <Route path="/join" element={<Join />} />
          <Route path="/docking1" element={<Docking1 />} />
          <Route path="/rocket/:userid" element={<Rocket />} />
          <Route path="/userinfo/:userid" element={<Userinfo />}></Route>;
          <Route path="/inventory/:userid" element={<Inventory />}></Route>;
        </Routes>
      </div>
    </div>
  )
}

export default App
