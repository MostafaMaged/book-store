package com.banquemisr.bms.auth.controller;

import com.banquemisr.bms.auth.dto.request.AuthenticationRequest;
import com.banquemisr.bms.auth.dto.request.RegistrationRequest;
import com.banquemisr.bms.auth.dto.response.AuthenticationResponse;
import com.banquemisr.bms.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.banquemisr.bms.auth.controller.AuthController.AUTH_API_PATH;
import static com.banquemisr.bms.shared.Constants.API_V1_PATH_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = API_V1_PATH_PREFIX + AUTH_API_PATH)
public class AuthController {

    protected static final String AUTH_API_PATH = "/auth";
    protected static final String LOGIN_API_PATH = "/login";
    protected static final String REGISTRATION_API_PATH = "/register";

    private final AuthService authService;

    @PostMapping(LOGIN_API_PATH)
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {

        AuthenticationResponse authenticationResponse = authService.login(request);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping(REGISTRATION_API_PATH)
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {

        AuthenticationResponse authenticationResponse = authService.register(registrationRequest);

        return ResponseEntity.ok(authenticationResponse);
    }
}
