package com.banquemisr.bms.auth.mapper;

import com.banquemisr.bms.auth.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthenticationResponse fromTokensToAuthenticationResponse(String bearerToken, String refreshToken) {

        return AuthenticationResponse.builder()
                .bearerToken(bearerToken)
                .refreshToken(refreshToken)
                .build();
    }
}
