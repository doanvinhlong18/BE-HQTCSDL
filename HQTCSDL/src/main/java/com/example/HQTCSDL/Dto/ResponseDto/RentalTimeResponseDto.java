package com.example.HQTCSDL.Dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentalTimeResponseDto {
    int id;
    @JsonFormat(pattern = "yyy-MM-dd")
    Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endTime;
    ResidentResponse resident;
}
