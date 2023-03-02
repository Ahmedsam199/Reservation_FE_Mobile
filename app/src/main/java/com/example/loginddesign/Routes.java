package com.example.loginddesign;
// Define All The Route Im gonna Use...//
public class Routes {
 String BASEURL="http://192.168.1.6:3000";
    String ServerRoute="https://reservationbackend.onrender.com";// Base URL if you want to work on server or Local...//
     String AUTH=ServerRoute+"/Auth/login"; // Login Route... //
     String USERROUTE=ServerRoute+"/api/User"; //Need Token...//
     String RESERVATIONROUTE=ServerRoute+"/api/Reservation"; //Need Token...//

     // NOTE : For Get All Reservation You Should Add User ID inside the URL For Example /api/Reservation/1
}
// For Now None Routes Need Token TokenLess
