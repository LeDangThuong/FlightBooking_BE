package com.example.FlightBooking.Controller.Auth.ForgotPassword;

import com.example.FlightBooking.Services.AuthJWT.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@CrossOrigin (value = {"http://localhost:7050", "https://flightbookingbe-production.up.railway.app", "http://localhost:5173"})
public class EmailOtpController {
    @Autowired
    private AuthenticationService authenticationService;
    @PutMapping("/auth/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return new ResponseEntity<>(authenticationService.forgot_password(email), HttpStatus.OK);
    }
}
