package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.mapper.DriverMapper;
import com.example.einvoice.core.message.DriverMessage;
import com.example.einvoice.core.message.TruckMessage;
import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.dto.DriverDto;
import com.example.einvoice.core.requests.update.UpdateDriverRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Contact;
import com.example.einvoice.model.Driver;
import com.example.einvoice.model.Truck;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.repository.DriverRepository;
import com.example.einvoice.service.ContactService;
import com.example.einvoice.service.DriverService;
import com.example.einvoice.service.TruckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final TruckService truckService;
    private final ContactRepository contactRepository;
    private final ContactService contactService;

    @Override
    @Transactional
    public GeneralResult create(CreateDriverRequest createDriverRequest) throws AlreadyExistsException, EntityNotFoundException {
        if (driverRepository.existsByIdentityNumber(createDriverRequest.getIdentityNumber())) {
            throw new AlreadyExistsException(DriverMessage.ALREADY_EXISTS.toString());
        }
        Truck truck = truckService.findById(createDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            throw new AlreadyExistsException(TruckMessage.ALREADY_HAS_DRIVER.toString());
        }

        Driver driver = DriverMapper.MAPPER.requestToEntity(createDriverRequest);

        Contact contact = ContactMapper.MAPPER.requestToEntity(createDriverRequest.getContact());
        contactService.create(contact);
        driver.setContact(contact);
        driver.setTruck(truck);
        truck.setDriver(driver);

        driverRepository.save(driver);

        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);

        return new DataResult<>(driverDto);

    }

    @Override
    @Transactional
    public GeneralResult update(UpdateDriverRequest updateDriverRequest, int driverId) throws EntityNotFoundException, AlreadyExistsException {
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(DriverMessage.NOT_FOUND.toString()));
        setUpdateDriverRequestToDriver(updateDriverRequest, driver);
        Truck truck = truckService.findById(updateDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            throw new AlreadyExistsException(TruckMessage.ALREADY_HAS_DRIVER.toString());
        }
        driverRepository.save(driver);
        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);
        return new DataResult<>(driverDto);
    }//TODO anlamlı hata mesajı dönmüyor


    @Override
    public GeneralResult getAll() {
        List<Driver> driverList = driverRepository.findAll();
        List<DriverDto> driverDtoList = driverList
                .stream()
                .map(DriverMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(driverDtoList);
    }

    @Override
    @Transactional
    public void delete(int driverId) throws EntityNotFoundException {
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(DriverMessage.NOT_FOUND.toString()));
        Contact contact = driver.getContact();
        contactService.delete(contact);
        driverRepository.delete(driver);
    }

    private void setUpdateDriverRequestToDriver(UpdateDriverRequest updateDriverRequest, Driver driver) throws EntityNotFoundException {

        Truck truck = truckService.findById(updateDriverRequest.getTruckId());

        driver.setSalary(updateDriverRequest.getSalary());
        driver.setTruck(truck);
    }
}
