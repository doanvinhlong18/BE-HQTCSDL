package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.BillDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bill")
public interface BillController {
    @PostMapping("/add/{idUsedService}")
    ResponseEntity<?> addBill(@PathVariable int idUsedService,@RequestBody BillDto billDto);
    @PutMapping("/update/{idBill}")
    //bo
    ResponseEntity<?> updateBill(@PathVariable int idBill,@RequestBody BillDto billDto);
    @DeleteMapping("/remove/{idBill}")
    //bo
    ResponseEntity<?> deleteBill(@PathVariable int idBill);
    @GetMapping("/{idBill}")
    //bo
    ResponseEntity<?> getBill(@PathVariable int idBill);
    @GetMapping("/all")
    ResponseEntity<?> getAllBills();
    @GetMapping("/resident/bill/{idResident}")
    ResponseEntity<?> getBillByResident(@PathVariable int idResident);
    //bo
    @GetMapping("/room/bill/{idRoom}")
    ResponseEntity<?> getAllBillsByRoom(@PathVariable int idRoom);
}
