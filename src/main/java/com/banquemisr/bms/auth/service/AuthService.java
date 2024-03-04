package com.banquemisr.bms.auth.service;


import com.banquemisr.bms.auth.dto.request.AuthenticationRequest;
import com.banquemisr.bms.auth.dto.request.RegistrationRequest;
import com.banquemisr.bms.auth.dto.response.AuthenticationResponse;
import com.banquemisr.bms.auth.mapper.AuthMapper;
import com.banquemisr.bms.config.security.JwtService;
import com.banquemisr.bms.shared.exception.InvalidPasswordException;
import com.banquemisr.bms.user.dto.UserDTO;
import com.banquemisr.bms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthMapper authMapper;

    public AuthenticationResponse login(AuthenticationRequest request) {

        UserDTO user = userService.fetchUserByEmail(request.getEmail());

        isPasswordValid(request, user);

        String bearerToken = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        return authMapper.fromTokensToAuthenticationResponse(bearerToken, refreshToken);
    }

    public AuthenticationResponse register(RegistrationRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        UserDTO user = userService.createUser(request);

        String bearerToken = jwtService.generateToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);

        return authMapper.fromTokensToAuthenticationResponse(bearerToken, refreshToken);
    }

    public String getAuthenticatedUserEmail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userDetails.getUsername();
    }

    private void isPasswordValid(AuthenticationRequest request, UserDTO user) {

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }
    }

}
