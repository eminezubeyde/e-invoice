package com.example.einvoice.service;


import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.core.result.GeneralResult;

public interface DriverService {

    GeneralResult create(DriverRequest driverRequest) throws AlreadyExistsException;

    GeneralResult update(DriverRequest driverRequest, int driverId);

    void delete(int driverId);
}
