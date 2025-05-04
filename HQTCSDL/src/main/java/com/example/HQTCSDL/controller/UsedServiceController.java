package com.example.HQTCSDL.controller;

import com.example.HQTCSDL.Dto.ServiceDto;
import com.example.HQTCSDL.Dto.UsedServiceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usedservice")
public interface UsedServiceController {
    @PostMapping("/add/{idResident}/{idService}")
    ResponseEntity<?> addUsedService(@PathVariable int idResident,
                                     @PathVariable int idService,
                                     @RequestBody UsedServiceDto usedServiceDto);
    @PutMapping("/update/{idUsedService}")
    ResponseEntity<?> updateUsedService(@PathVariable int idUsedService,
                                        @RequestBody UsedServiceDto usedServiceDto);
    @GetMapping("/{idUsedService}")
    ResponseEntity<?> getUsedService(@PathVariable int idUsedService);
    @GetMapping("/all")
    ResponseEntity<?> getAllUsedServices();
    @GetMapping("/resident/{idResident}")
    ResponseEntity<?> getUsedServiceByResident(@PathVariable int idResident);
}
