package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.AddResidentToRoomDto;
import com.example.HQTCSDL.Dto.RentalTimeDto;
import com.example.HQTCSDL.Dto.ResidentDto;
import com.example.HQTCSDL.Dto.ResponseDto.RentalTimeResponseDto;
import com.example.HQTCSDL.Dto.ResponseDto.RoomResponseDto;
import com.example.HQTCSDL.Dto.RoomDto;
import com.example.HQTCSDL.Entity.RentalTimeEntity;
import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.repository.RentalTimeRepository;
import com.example.HQTCSDL.repository.ResidentRepository;
import com.example.HQTCSDL.repository.RoomRepository;
import com.example.HQTCSDL.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ResidentRepository residentRepository;
    private final RentalTimeRepository rentalTimeRepository;

    public RoomServiceImpl(RoomRepository roomRepository, ResidentRepository residentRepository, RentalTimeRepository rentalTimeRepository) {
        this.roomRepository = roomRepository;
        this.residentRepository = residentRepository;
        this.rentalTimeRepository = rentalTimeRepository;
    }

    @Override
    public ResponseEntity<?> editRoom(int idRoom, RoomEntity roomEntity) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(idRoom);
        if (roomEntityOptional.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay phong"), HttpStatus.NOT_FOUND);
        }
        RoomEntity room = roomEntityOptional.get();
        room.setName(roomEntity.getName());
        room.setArea(roomEntity.getArea());
        room.setRentPrice(roomEntity.getRentPrice());
        room.setRentStatus(roomEntity.getRentStatus());
        room.setType(roomEntity.getType());
        return new ResponseEntity<>(roomRepository.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAllResidentsFromRoom(int idRoom) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(idRoom);
        if (roomEntityOptional.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay phong"), HttpStatus.NOT_FOUND);
        }
        RoomEntity room = roomEntityOptional.get();
        // Xoá tất cả rentalTimes (nếu có)
        if (room.getRentalTimes() != null) {
            for (RentalTimeEntity rentalTime : room.getRentalTimes()) {
                rentalTime.setRoom(null); // huỷ liên kết với room
                // Nếu bạn có rentalTimeRepository, bạn có thể lưu lại rentalTime tại đây
                // rentalTimeRepository.save(rentalTime);
            }
            room.getRentalTimes().clear(); // xóa khỏi danh sách trong Room (không ảnh hưởng DB nếu không có orphanRemoval)
        }

        // Các thao tác khác (nếu cần)
        room.setRentStatus("available");

        // Xoá tất cả bills (nếu có)
        if (room.getBills() != null) {
            room.getBills().clear();
        }

        // Đặt lại trạng thái phòng
        room.setRentStatus("available");
        roomRepository.save(room);
        return new ResponseEntity<>(Collections.singletonMap("message", "Xoa phong thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteResidentFromRoom(int idResident) {
        Optional<ResidentEntity> residentEntity = residentRepository.findById(idResident);
        if (residentEntity.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay cu dan"), HttpStatus.NOT_FOUND);
        }
        ResidentEntity resident = residentEntity.get();
        resident.getRentalTimes().getLast().setRoom(null);
        residentRepository.save(resident);
        return new ResponseEntity<>(Collections.singletonMap("message", "Xoa cu dan khoi phong thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addResidentToRoom(int idRoom, RentalTimeDto rentalTimeDto) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(idRoom);
        if (roomEntityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RoomEntity room = roomEntityOptional.get();
        RentalTimeEntity rentalTimeEntity = getRentalTimeFromDto(rentalTimeDto);
        ResidentDto residentDto = rentalTimeDto.getResident();
        Optional<ResidentEntity> residentOpt = residentRepository.findByIdNumber(residentDto.getIdNumber());
        ResidentEntity residentEntity;
        if (residentOpt.isPresent()) {
            residentEntity = residentOpt.get();
            residentEntity.getRentalTimes().add(rentalTimeEntity);
        } else {
            residentEntity = getResidentFromDto(residentDto);
            residentRepository.save(residentEntity);
        }

        rentalTimeEntity.setRoom(room);
        rentalTimeEntity.setResident(residentEntity);
        System.out.println(rentalTimeEntity);
        room.setRentStatus("rented");
        roomRepository.save(room);
        rentalTimeRepository.save(rentalTimeEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Thêm thành công"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addRoom(RoomDto roomDto) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setArea(roomDto.getArea());
        roomEntity.setRentPrice(roomDto.getRentPrice());
        roomEntity.setRentStatus(roomDto.getRentStatus());
        roomEntity.setType(roomDto.getType());
        roomEntity.setName(roomDto.getName());
        return new ResponseEntity<>(roomRepository.save(roomEntity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRoom(int idRoom) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(idRoom);
        if (roomEntityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomEntityOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllRooms() {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        if (roomEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RoomResponseDto> roomResponseDtoList = new ArrayList<>();
        for(RoomEntity roomEntity : roomEntities) {
            RoomResponseDto roomResponseDto = getRoomResponseDto(roomEntity);
            List<RentalTimeResponseDto> rentalTimeResponseDtoList = new ArrayList<>();
            for(RentalTimeEntity rentalTimeEntity : roomEntity.getRentalTimes()) {
                rentalTimeResponseDtoList.add(getRentalTimeResponseDto(rentalTimeEntity));
            }
            roomResponseDto.setRentalTimes(rentalTimeResponseDtoList);
            roomResponseDtoList.add(roomResponseDto);
        }

        return new ResponseEntity<>(roomResponseDtoList, HttpStatus.OK);
    }
    public RentalTimeEntity getRentalTimeFromDto(RentalTimeDto rentalTimeDto) {
        RentalTimeEntity rentalTimeEntity = new RentalTimeEntity();
        rentalTimeEntity.setStartDate(rentalTimeDto.getStartTime());
        rentalTimeEntity.setEndDate(rentalTimeDto.getEndTime());
        return rentalTimeEntity;
    }
    public ResidentEntity getResidentFromDto(ResidentDto residentDto) {
        ResidentEntity residentEntity = new ResidentEntity();
        residentEntity.setPhone(residentDto.getPhone());
        residentEntity.setEmail(residentDto.getEmail());
        residentEntity.setIdNumber(residentDto.getIdNumber());
        residentEntity.setName(residentDto.getName());
        residentEntity.setDateOfBirth(residentDto.getDateOfBirth());
        return residentEntity;
    }
    public RoomResponseDto getRoomResponseDto(RoomEntity roomEntity) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setId(roomEntity.getId());
        roomResponseDto.setArea(roomEntity.getArea());
        roomResponseDto.setRentPrice(roomEntity.getRentPrice());
        roomResponseDto.setRentStatus(roomEntity.getRentStatus());
        roomResponseDto.setType(roomEntity.getType());
        roomResponseDto.setName(roomEntity.getName());
        return roomResponseDto;
    }
    public RentalTimeResponseDto getRentalTimeResponseDto(RentalTimeEntity rentalTimeEntity) {
        RentalTimeResponseDto rentalTimeResponseDto = new RentalTimeResponseDto();
        rentalTimeResponseDto.setId(rentalTimeEntity.getId());
        rentalTimeResponseDto.setStartTime(rentalTimeEntity.getStartDate());
        rentalTimeResponseDto.setEndTime(rentalTimeEntity.getEndDate());
        rentalTimeResponseDto.setResident(rentalTimeResponseDto.getResident());
        return rentalTimeResponseDto;
    }
}
