package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.UserResponseDto;
import com.example.HQTCSDL.Entity.UserEntity;

public class User {
    public static UserResponseDto getUserResponseDto(UserEntity userEntity) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userEntity.getId());
        userResponseDto.setEmail(userEntity.getEmail());
        userResponseDto.setPhone(userEntity.getPhone());
        userResponseDto.setUsername(userEntity.getUsername());
        userResponseDto.setPassword(userEntity.getPassword());
        userResponseDto.setPosition(userEntity.getPosition());
        userResponseDto.setFullName(userEntity.getFullName());
        userResponseDto.setDateOfBirth(userEntity.getDateOfBirth());
        return userResponseDto;
    }
}
