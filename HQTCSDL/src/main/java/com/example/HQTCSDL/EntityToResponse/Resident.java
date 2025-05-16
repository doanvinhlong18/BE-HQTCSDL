package com.example.HQTCSDL.EntityToResponse;

import com.example.HQTCSDL.Dto.ResponseDto.ResidentResponse;
import com.example.HQTCSDL.Entity.ResidentEntity;

public class Resident {
    public static ResidentResponse getResidentResponse(ResidentEntity resident) {
        ResidentResponse residentResponse = new ResidentResponse();
        residentResponse.setId(resident.getId());
        residentResponse.setName(resident.getName());
        residentResponse.setPhone(resident.getPhone());
        residentResponse.setEmail(resident.getEmail());
        residentResponse.setIdNumber(resident.getIdNumber());
        residentResponse.setDateOfBirth(resident.getDateOfBirth());
        return residentResponse;
    }
}
