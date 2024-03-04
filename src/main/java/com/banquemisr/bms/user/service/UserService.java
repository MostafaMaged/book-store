package com.banquemisr.bms.user.service;


import com.banquemisr.bms.auth.dto.request.RegistrationRequest;
import com.banquemisr.bms.user.dto.UserDTO;
import com.banquemisr.bms.user.mapper.UserMapper;
import com.banquemisr.bms.user.model.User;
import com.banquemisr.bms.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(RegistrationRequest registrationRequest) {

        User newUser = userMapper.fromRegistrationRequestToEntity(registrationRequest);

        User persistedUser = userRepository.save(newUser);

        UserDTO persistedUserDTO = userMapper.fromEntityToDTO(persistedUser);

        return persistedUserDTO;
    }

    public UserDTO fetchUserByEmail(String email) {

        User fetchedUserEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(String.format("user with email %s not found", email)));

        UserDTO fetecheUserDTO = userMapper.fromEntityToDTO(fetchedUserEntity);

        return fetecheUserDTO;
    }
}