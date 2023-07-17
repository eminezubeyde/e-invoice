package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Truck;

public interface TruckService {
    GeneralResult create(CreateTruckRequest createTruckRequest);
    GeneralResult getAll();

    void delete(int truckId) throws EntityNotFoundException;


    boolean existsById(int truckId);

    Truck findById(int truckId) throws EntityNotFoundException;
}
