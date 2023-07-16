package com.example.einvoice.service;

import com.example.einvoice.core.requests.TruckRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface TruckService {
    GeneralResult create(TruckRequest truckRequest);

    GeneralResult update(TruckRequest truckRequest, int truckId);

    void delete(int truckId);

}
