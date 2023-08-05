package com.example.einvoice.service.impl;

import com.example.einvoice.core.constant.security.Roles;
import com.example.einvoice.core.dto.DriverDto;
import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.mapper.DriverMapper;
import com.example.einvoice.core.constant.message.DriverMessage;
import com.example.einvoice.core.constant.message.TruckMessage;
import com.example.einvoice.core.requests.create.CreateDriverRequest;
import com.example.einvoice.core.requests.update.UpdateDriverRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.entity.Driver;
import com.example.einvoice.entity.Role;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.DriverRepository;
import com.example.einvoice.repository.RoleRepository;
import com.example.einvoice.service.ContactService;
import com.example.einvoice.service.DriverService;
import com.example.einvoice.service.RoleService;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final TruckService truckService;
    private ContactService contactService;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Override
    @Transactional
    public GeneralResult create(CreateDriverRequest createDriverRequest) throws AlreadyExistsException, EntityNotFoundException {
        log.info("driver create method started with request : " + createDriverRequest);
        if (driverRepository.existsByIdentityNumber(createDriverRequest.getIdentityNumber())) {
            log.error("driver already exists");
            throw new AlreadyExistsException(getMessage(DriverMessage.ALREADY_EXISTS.getKey()));

        }
        Truck truck = truckService.findById(createDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            log.error("truck already has driver");
            throw new AlreadyExistsException(getMessage(TruckMessage.ALREADY_HAS_DRIVER.getKey()));
        }

        Driver driver = DriverMapper.MAPPER.requestToEntity(createDriverRequest);
        driver.setPassword(passwordEncoder.encode(createDriverRequest.getPassword()));
        Optional<Role> role = roleService.getByName(Roles.DRIVER);
        if (role.isEmpty()) {
            log.error("role not found");
            throw new EntityNotFoundException(getMessage("role not found"));
        }
        driver.setRoles(List.of(role.get()));

        Contact contact = ContactMapper.MAPPER.requestToEntity(createDriverRequest.getContact());
        contactService.create(contact);
        driver.setContact(contact);
        driver.setTruck(truck);
        truck.setDriver(driver);

        driverRepository.save(driver);

        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);
        log.info("create driver ended..");
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDto);

    }

    @Override
    @Transactional
    public GeneralResult update(UpdateDriverRequest updateDriverRequest, int driverId) throws EntityNotFoundException, AlreadyExistsException {
        log.info("driver update method started with request : " + updateDriverRequest);
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(DriverMessage.NOT_FOUND.getKey())));
        setUpdateDriverRequestToDriver(updateDriverRequest, driver);
        Truck truck = truckService.findById(updateDriverRequest.getTruckId());

        if (truck.getDriver() != null) {
            log.error("truck already has driver");
            throw new AlreadyExistsException(getMessage(TruckMessage.ALREADY_HAS_DRIVER.getKey()));
        }
        driverRepository.save(driver);
        DriverDto driverDto = DriverMapper.MAPPER.entityToResponse(driver);
        log.info("update driver ended..");
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDto);
    }


    @Override
    @Transactional
    public GeneralResult getAll() {
        log.info("getAll driver method started" );
        List<Driver> driverList = driverRepository.findAll();
        List<DriverDto> driverDtoList = driverList
                .stream()
                .map(DriverMapper.MAPPER::entityToResponse)
                .toList();
        log.info("getAll driver method ended..");
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, driverDtoList);
    }

    @Override
    public Driver getById(int driverId) throws EntityNotFoundException {
        log.info("get driver method started" );
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(DriverMessage.NOT_FOUND.toString()));
        log.info("get driver method ended..");
        return driver;
    }

    @Override
    @Transactional
    public void delete(int driverId) throws EntityNotFoundException {
        log.info("delete driver method started");
        Driver driver = driverRepository
                .findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(DriverMessage.NOT_FOUND.getKey())));
        Contact contact = driver.getContact();
        contactService.deleteByID(contact);
        log.info("delete driver method ended..");
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

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }
}
