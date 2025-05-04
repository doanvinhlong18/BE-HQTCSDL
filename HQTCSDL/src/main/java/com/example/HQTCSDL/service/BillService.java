package com.example.HQTCSDL.service;

import com.example.HQTCSDL.Dto.BillDto;
import org.springframework.http.ResponseEntity;

public interface BillService {
    ResponseEntity<?> addBill(int idUsedService, BillDto billDto);
    ResponseEntity<?> updateBill(int idBill, BillDto billDto);
    ResponseEntity<?> deleteBill(int idBill);
    ResponseEntity<?> getBill(int idBill);
    ResponseEntity<?> getAllBills();
    ResponseEntity<?> getBillByResident(int idResident);
    ResponseEntity<?> getAllBillsByRoom(int idRoom);
}
