package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.BillDto;
import com.example.HQTCSDL.Entity.BillEntity;
import com.example.HQTCSDL.Entity.ResidentEntity;
import com.example.HQTCSDL.Entity.RoomEntity;
import com.example.HQTCSDL.Entity.UsedServiceEntity;
import com.example.HQTCSDL.repository.BillRepository;
import com.example.HQTCSDL.repository.ResidentRepository;
import com.example.HQTCSDL.repository.RoomRepository;
import com.example.HQTCSDL.repository.UsedServiceRepository;
import com.example.HQTCSDL.service.BillService;
import com.example.HQTCSDL.service.UsedServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {

    private final ResidentRepository residentRepository;
    private final UsedServiceRepository usedServiceRepository;
    private final BillRepository billRepository;
    private final RoomRepository roomRepository;

    public BillServiceImpl(ResidentRepository residentRepository, UsedServiceRepository usedServiceRepository, BillRepository billRepository, RoomRepository roomRepository) {
        this.residentRepository = residentRepository;
        this.usedServiceRepository = usedServiceRepository;
        this.billRepository = billRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ResponseEntity<?> addBill(int idUsedService, BillDto billDto) {
        Optional<UsedServiceEntity> usedServiceEntity= usedServiceRepository.findById(idUsedService);
        if (usedServiceEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay used service"), HttpStatus.NOT_FOUND);
        }
        UsedServiceEntity usedService=usedServiceEntity.get();
        BillEntity billEntity = new BillEntity();
        billEntity.setPaymentType(billDto.getPaymentType());
        billEntity.setName(billDto.getName());
        billEntity.setNote(billDto.getNote());
        billEntity.setPaymentAmount(usedService.getQuantity() * usedService.getService().getPrice());
        billEntity.setPaymentDate(new Date());
        billEntity.setRoom(billDto.getRoom());
        billEntity.setUsedService(usedService);
        billRepository.save(billEntity);
        return new ResponseEntity<>(Collections.singletonMap("message", "Them hoa don thanh cong"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateBill(int idBill, BillDto billDto) {
        Optional<BillEntity> billEntity= billRepository.findById(idBill);
        if (billEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay hoa don"), HttpStatus.NOT_FOUND);
        }
        BillEntity bill=billEntity.get();
        bill.setName(billDto.getName());
        bill.setNote(billDto.getNote());
        bill.setPaymentType(billDto.getPaymentType());
        billRepository.save(bill);
        return new ResponseEntity<>(Collections.singletonMap("message", "Cap nhat hoa don thanh cong"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBill(int idBill) {
        return null;
    }

    @Override
    public ResponseEntity<?> getBill(int idBill) {
        Optional<BillEntity> billEntity= billRepository.findById(idBill);
        if (billEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Không tìm thấy hóa đơn"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(billEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBills() {
        List<BillEntity> billEntity= billRepository.findAll();
        if (billEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong co hoa don nao"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(billEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getBillByResident(int idResident) {
        Optional<ResidentEntity> residentEntity= residentRepository.findById(idResident);
        if (residentEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Không tìm thấy khách hàng"), HttpStatus.NOT_FOUND);
        }
        List<BillEntity> billEntity= new ArrayList<>();
        for(UsedServiceEntity usedServiceEntity: residentEntity.get().getUsedServices()){
            billEntity.addAll(usedServiceEntity.getBills());
        }
        return new ResponseEntity<>(billEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllBillsByRoom(int idRoom) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(idRoom);
        if (roomEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay phong"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomEntity.get().getBills(), HttpStatus.OK);
    }
    public BillEntity getBillFromDto(BillDto billDto) {
        BillEntity bill = new BillEntity();
        bill.setId(billDto.getId());
        bill.setName(billDto.getName());
        bill.setNote(billDto.getNote());
        bill.setPaymentAmount(billDto.getPaymentAmount());
        bill.setPaymentType(billDto.getPaymentType());
        return bill;
    }
}
