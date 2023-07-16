package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.mapper.DriverMapper;
import com.example.einvoice.core.message.DriverMessage;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.responses.DriverResponse;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Driver;
import com.example.einvoice.repository.DriverRepository;
import com.example.einvoice.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public GeneralResult create(DriverRequest driverRequest) throws AlreadyExistsException {
        if (driverRepository.existsByIdentityNumber(driverRequest.getIdentityNumber())) {
            throw new AlreadyExistsException(DriverMessage.ALREADY_EXISTS.toString());
        }
        Driver driver = DriverMapper.MAPPER.requestToEntity(driverRequest);
        driverRepository.save(driver);
        DriverResponse driverResponse = DriverMapper.MAPPER.entityToResponse(driver);

        return new DataResult<>(driverResponse);
    }

    @Override
    public GeneralResult update(DriverRequest driverRequest, int driverId) {
        return null;
    }

    @Override
    public void delete(int driverId) {

    }
}
