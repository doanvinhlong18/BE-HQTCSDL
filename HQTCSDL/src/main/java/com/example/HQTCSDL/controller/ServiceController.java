package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.ServiceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/service")
public interface ServiceController {
    @PostMapping("/add")
    ResponseEntity<?> addService(@RequestBody ServiceDto serviceDto);
    @DeleteMapping("/remove/{idService}")
    ResponseEntity<?> removeService(@PathVariable int idService);
    @PutMapping("/update/{idService}")
    ResponseEntity<?> updateService(@PathVariable int idService,@RequestBody ServiceDto serviceDto);
    @GetMapping("/{idService}")
    ResponseEntity<?> getServiceById(@PathVariable int idService);
    @GetMapping("/all")
    ResponseEntity<?> getAllService();
}
