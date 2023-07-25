package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/truck")
@RequiredArgsConstructor
public class TruckController {
    private final TruckService truckService;

    @PostMapping
    public GeneralResult create(@Valid @RequestBody CreateTruckRequest createTruckRequest) throws AlreadyExistsException {
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
