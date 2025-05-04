package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.LoginDto;
import com.example.HQTCSDL.Dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> login(LoginDto loginDto);
    ResponseEntity<?> logout();
    ResponseEntity<?> register(UserDto userDto);

    ResponseEntity<?> addUser(UserDto userDto);

    ResponseEntity<?> updateUserById(int id, UserDto userDto);

    ResponseEntity<?> deleteUserById(int id);

    ResponseEntity<?> getUserById(int id);

    ResponseEntity<?> getAllUsers();
}
