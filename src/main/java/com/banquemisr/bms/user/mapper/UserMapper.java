package com.banquemisr.bms.user.mapper;

import com.banquemisr.bms.auth.dto.request.RegistrationRequest;
import com.banquemisr.bms.user.dto.UserDTO;
import com.banquemisr.bms.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.banquemisr.bms.user.model.enums.Role.USER;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO fromEntityToDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .deactivated(user.isDeactivated())
                .build();
    }

    public User fromRegistrationRequestToEntity(RegistrationRequest registrationRequest) {

        return User.builder()
                .name(registrationRequest.getName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .role(USER)
                .deactivated(false)
                .build();
    }
}
