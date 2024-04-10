package com.example.FlightBooking.Controller.Auth.ForgotPassword;

import com.example.FlightBooking.DTOs.Request.Auth.ResetPasswordDTO;
import com.example.FlightBooking.Services.AuthJWT.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@CrossOrigin (value = {"*", "https://flightbookingbe-production.up.railway.app/**"})
public class ResetPasswordController {
    @Autowired
    private AuthenticationService authenticationService;


    @PutMapping("/auth/reset-password")
    public ResponseEntity<String> ResetPassword(@ModelAttribute ResetPasswordDTO resetPasswordDTO){
        return new ResponseEntity<>(authenticationService.reset_password(resetPasswordDTO.getEmail(), resetPasswordDTO.getOtp(), resetPasswordDTO.getNewPassword(), resetPasswordDTO.getConfirmPassword()), HttpStatus.OK);
    }

}
