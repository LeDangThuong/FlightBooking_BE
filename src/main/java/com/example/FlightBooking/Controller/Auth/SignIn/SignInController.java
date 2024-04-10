package com.example.FlightBooking.Controller.Auth.SignIn;

import com.example.FlightBooking.DTOs.Request.Auth.SignInDTO;
import com.example.FlightBooking.DTOs.Response.Auth.LoginResponse;
import com.example.FlightBooking.Models.Tokens;
import com.example.FlightBooking.Models.Users;
import com.example.FlightBooking.Repositories.TokenRepository;
import com.example.FlightBooking.Services.AuthJWT.AuthenticationService;
import com.example.FlightBooking.Services.AuthJWT.JwtRefreshService;
import com.example.FlightBooking.Services.AuthJWT.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (value = {"http://localhost:7050", "https://flightbookingbe-production.up.railway.app"})
public class SignInController {
    private final JwtService jwtService;
    private final JwtRefreshService jwtRefreshService;
    private final AuthenticationService authenticationService;
    private final TokenRepository tokenRepository;
    public SignInController(JwtService jwtService, JwtRefreshService jwtRefreshService, AuthenticationService authenticationService, TokenRepository tokenRepository) {
        this.jwtService = jwtService;
        this.jwtRefreshService = jwtRefreshService;
        this.authenticationService = authenticationService;
        this.tokenRepository = tokenRepository;
    }
    @PostMapping("/auth/signin")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody SignInDTO loginUserDto) {
        Users authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtTokenAccess = jwtService.generateToken(authenticatedUser);
        String jwtTokenRefresh = jwtRefreshService.generateToken(authenticatedUser);
        Tokens tokens = new Tokens();
        tokens.setUser(authenticatedUser);
        tokens.setTokenRefresh(jwtTokenRefresh);
        tokens.setTokenAccess(jwtTokenAccess);
        tokens.setExpireTime(jwtService.getExpirationTime());
        tokens.setExpireRefreshTime(jwtRefreshService.getExpirationTime());
        tokenRepository.save(tokens);

        //
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setTokenAccess(jwtTokenAccess);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUsername(authenticatedUser.getUsername());
        loginResponse.setRole(authenticatedUser.getRole());
        loginResponse.setTokenRefresh(jwtTokenRefresh);
        loginResponse.setExpiresRefreshIn(jwtRefreshService.getExpirationTime());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
