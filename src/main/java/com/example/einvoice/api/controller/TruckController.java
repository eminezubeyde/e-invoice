package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.TruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/truck")
@RequiredArgsConstructor
public class TruckController {
    private final TruckService truckService;

    @PostMapping
    public GeneralResult create(@RequestBody TruckRequest truckRequest) {
        return truckService.create(truckRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody TruckRequest truckRequest, @RequestParam int truckId) {
        return truckService.update(truckRequest, truckId);
    }

    @DeleteMapping
    public void delete(@RequestParam int truckId) {
        truckService.delete(truckId);
    }
}
