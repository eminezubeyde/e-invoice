package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/truck")
@RequiredArgsConstructor
public class TruckController {
    private final TruckService truckService;

    @PostMapping
    public GeneralResult create(@RequestBody CreateTruckRequest createTruckRequest) {
        return truckService.create(createTruckRequest);
    }

    @GetMapping
    public GeneralResult getAll() {
        return truckService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestParam int truckId) throws EntityNotFoundException {
        truckService.delete(truckId);
    }
}
