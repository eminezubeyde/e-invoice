package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.requests.update.UpdateDriverRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {
   private final DriverService driverService;
    @PostMapping
    public GeneralResult create(@RequestBody CreateDriverRequest createDriverRequest) throws AlreadyExistsException, EntityNotFoundException {
        return driverService.create(createDriverRequest);
    }
    @PutMapping
    public GeneralResult update(@RequestBody UpdateDriverRequest updateDriverRequest, @RequestParam int driverId) throws EntityNotFoundException {
        return driverService.update(updateDriverRequest,driverId);
    }
    @GetMapping
    public GeneralResult getAll(){
        return driverService.getAll();
    }
    @DeleteMapping
    public void delete(@RequestParam int driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }
}
