package com.example.HQTCSDL.controllerImpl;

import com.example.HQTCSDL.Dto.BillDto;
import com.example.HQTCSDL.controller.BillController;
import com.example.HQTCSDL.service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillControllerImpl implements BillController {
    private final BillService billService;

    public BillControllerImpl(BillService billService) {
        this.billService = billService;
    }

    @Override
    public ResponseEntity<?> addBill(int idUsedService, BillDto billDto) {
        return billService.addBill(idUsedService, billDto);
    }

    @Override
    public ResponseEntity<?> updateBill(int idBill, BillDto billDto) {
        return billService.updateBill(idBill, billDto);
    }

    @Override
    public ResponseEntity<?> deleteBill(int idBill) {
        return billService.deleteBill(idBill);
    }

    @Override
    public ResponseEntity<?> getBill(int idBill) {
        return billService.getBill(idBill);
    }

    @Override
    public ResponseEntity<?> getAllBills() {
        return billService.getAllBills();
    }

    @Override
    public ResponseEntity<?> getBillByResident(int idResident) {
        return billService.getBillByResident(idResident);
    }

    @Override
    public ResponseEntity<?> getAllBillsByRoom(int idRoom) {
        return billService.getAllBillsByRoom(idRoom);
    }
}
