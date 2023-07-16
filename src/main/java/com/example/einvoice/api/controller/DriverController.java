package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.DriverResponse;
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
    public GeneralResult create(@RequestBody DriverRequest driverRequest) throws AlreadyExistsException {
        return driverService.create(driverRequest);
    }
    @PutMapping
    public GeneralResult update(@RequestBody DriverRequest driverRequest,@RequestParam int driverId){
        return driverService.update(driverRequest,driverId);
    }
    @DeleteMapping
    public void delete(@RequestParam int driverId){
        driverService.delete(driverId);
    }
}
