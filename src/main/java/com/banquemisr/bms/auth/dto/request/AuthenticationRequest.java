package com.banquemisr.bms.auth.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email should be email like pattern: example@domain.com")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 8, max = 20, message = "password length must be between 8 and 20 characters")
    private String password;
}
