package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.UserDto;
import com.example.HQTCSDL.controller.UserController;
import com.example.HQTCSDL.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> addUser(UserDto userDto) {
        return userService.addUser(userDto);
    }

    @Override
    public ResponseEntity<?> updateUser(int idUser, UserDto userDto) {
        return userService.updateUserById(idUser, userDto);
    }

    @Override
    public ResponseEntity<?> removeUser(int idUser) {
        return userService.deleteUserById(idUser);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ResponseEntity<?> getUserById(int idUser) {
        return userService.getUserById(idUser);
    }
}
