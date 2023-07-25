package com.example.einvoice.service;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Truck;

public interface TruckService {
    GeneralResult create(CreateTruckRequest createTruckRequest) throws AlreadyExistsException;
    GeneralResult getAll();

    void delete(int truckId) throws EntityNotFoundException;


    boolean existsById(int truckId);

    Truck findById(int truckId) throws EntityNotFoundException;

    Truck getByPlate(String plate);
}
