package com.banquemisr.bms.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email should be email like pattern: example@domain.com")
    private String email;

    @NotBlank(message = "name cannot be empty")
    @Min(value = 3, message = "name cannot be less than 3 characters")
    @Max(value = 100, message = "name cannot be more than 100 characters")
    private String name;

    @NotBlank(message = "password cannot be empty")
    @Min(value = 8, message = "password cannot be less than 8 characters")
    @Max(value = 20, message = "password cannot be more than 20 characters")
    private String password;
}
