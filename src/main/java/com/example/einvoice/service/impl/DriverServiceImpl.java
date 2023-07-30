package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.DriverDto;
import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.mapper.DriverMapper;
import com.example.einvoice.core.message.DriverMessage;
import com.example.einvoice.core.message.TruckMessage;
import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.requests.update.UpdateDriverRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.entity.Driver;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.DriverRepository;
import com.example.einvoice.service.ContactService;
import com.example.einvoice.service.DriverService;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Setter
public class DriverServiceImpl implements DriverService {
    private  DriverRepository driverRepository;
    private  TruckService truckService;
    private  ContactService contactService;
    private  MessageSource messageSource;

    @Override
    @Transactional
    public GeneralResult create(CreateDriverRequest createDriverRequest) throws AlreadyExistsException, EntityNotFoundException {
        if (driverRepository.existsByIdentityNumber(createDriverRequest.getIdentityNumber())) {
            throw new AlreadyExistsException(getMessage(DriverMessage.ALREADY_EXISTS.getKey()));
        }
        Truck truck = truckService.findById(createDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            throw new AlreadyExistsException(getMessage(TruckMessage.ALREADY_HAS_DRIVER.getKey()));
        }

        Driver driver = DriverMapper.MAPPER.requestToEntity(createDriverRequest);

        Contact contact = ContactMapper.MAPPER.requestToEntity(createDriverRequest.getContact());
        contactService.create(contact);
        driver.setContact(contact);
        driver.setTruck(truck);
        truck.setDriver(driver);

        driverRepository.save(driver);

        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);

        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDto);

    }

    @Override
    @Transactional
    public GeneralResult update(UpdateDriverRequest updateDriverRequest, int driverId) throws EntityNotFoundException, AlreadyExistsException {
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(DriverMessage.NOT_FOUND.getKey())));
        setUpdateDriverRequestToDriver(updateDriverRequest, driver);
        Truck truck = truckService.findById(updateDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            throw new AlreadyExistsException(getMessage(TruckMessage.ALREADY_HAS_DRIVER.getKey()));
        }
        driverRepository.save(driver);
        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDto);
    }


    @Override
    public GeneralResult getAll() {
        List<Driver> driverList = driverRepository.findAll();
        List<DriverDto> driverDtoList = driverList
                .stream()
                .map(DriverMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDtoList);
    }

    @Override
    @Transactional
    public void delete(int driverId) throws EntityNotFoundException {
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(DriverMessage.NOT_FOUND.getKey())));
        Contact contact = driver.getContact();
        contactService.deleteByID(contact);
        driverRepository.delete(driver);
    }

    private void setUpdateDriverRequestToDriver(UpdateDriverRequest updateDriverRequest, Driver driver) throws EntityNotFoundException {

        Truck truck = truckService.findById(updateDriverRequest.getTruckId());

        driver.setSalary(updateDriverRequest.getSalary());
        driver.setTruck(truck);
    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
