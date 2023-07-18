package com.example.einvoice.service;


import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.requests.update.UpdateDriverRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface DriverService {

    GeneralResult create(CreateDriverRequest createDriverRequest) throws AlreadyExistsException, EntityNotFoundException;

    GeneralResult update(UpdateDriverRequest updateDriverRequest, int driverId) throws EntityNotFoundException, AlreadyExistsException;
    GeneralResult getAll();

    void delete(int driverId) throws EntityNotFoundException;



}
