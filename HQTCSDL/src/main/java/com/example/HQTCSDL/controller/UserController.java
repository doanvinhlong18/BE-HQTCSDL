package com.example.HQTCSDL.controller;


import com.example.HQTCSDL.Dto.LoginDto;
import com.example.HQTCSDL.Dto.UserDto;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserController {
    @PostMapping("/add")
    ResponseEntity<?> addUser(@RequestBody UserDto userDto);

    @PutMapping("/update/{idUser}")
    ResponseEntity<?> updateUser(@PathVariable int idUser,@RequestBody UserDto userDto);

    @DeleteMapping("/remove/{idUser}")
    ResponseEntity<?> removeUser(@PathVariable int idUser);

    @GetMapping("/all")
    ResponseEntity<?> getAllUsers();

    @GetMapping("/{idUser}")
    ResponseEntity<?> getUserById(@PathVariable int idUser);

    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto);
}
