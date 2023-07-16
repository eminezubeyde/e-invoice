package com.example.einvoice.service;

import com.example.einvoice.core.requests.TruckRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {
    private final TruckRepository truckRepository;
    @Override
    public GeneralResult create(TruckRequest truckRequest) {
        return null;
    }

    @Override
    public GeneralResult update(TruckRequest truckRequest, int truckId) {
        return null;
    }

    @Override
    public void delete(int truckId) {

    }
}
