package com.example.HQTCSDL.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    int id;
    String username;
    String fullName;
    Date dateOfBirth;
    String phone;
    String email;
    String position;
    List<BuildingResponseDto> buildings;
}
