package com.example.einvoice.service.impl;

import com.example.einvoice.core.constant.message.TruckMessage;
import com.example.einvoice.core.dto.TruckDto;
import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.TruckMapper;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.TruckRepository;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class TruckServiceImpl implements TruckService {
    private final TruckRepository truckRepository;
    private final MessageSource messageSource;

    @Override
    public GeneralResult create(CreateTruckRequest createTruckRequest) throws AlreadyExistsException {
        log.info("truck create method started with request : " + createTruckRequest);
        if (truckRepository.existsByPlate(createTruckRequest.getPlate())) {
            log.error("truck already exists");
            throw new AlreadyExistsException(getMessage(TruckMessage.ALREADY_EXISTS.getKey()));
        }
        Truck truck = TruckMapper.MAPPER.requestToEntity(createTruckRequest);
        truckRepository.save(truck);
        TruckDto truckResponse = TruckMapper.MAPPER.entityToResponse(truck);
        log.info("truck create method ended");
        return new DataResult<>(getMessage(TruckMessage.SUCCESSFUL.getKey()), true, truckResponse);
    }


    @Override
    public GeneralResult getAll() {
        log.info("getAll truck method started");
        List<Truck> truckList = truckRepository.findAll();
        List<TruckDto> truckResponseList = truckList
                .stream()
                .map(TruckMapper.MAPPER::entityToResponse)
                .toList();
        log.info("truck getAll method ended");
        return new DataResult<>(getMessage(TruckMessage.SUCCESSFUL.getKey()), true, truckResponseList);
    }

    @Override
    public void delete(int truckId) throws EntityNotFoundException {
        log.info("delete truck method started");
        Truck truck = truckRepository
                .findById(truckId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(TruckMessage.NOT_FOUND.getKey())));
        log.info("truck delete method ended");
        truckRepository.delete(truck);
    }

    @Override
    public boolean existsById(int truckId) {
        return truckRepository.existsById(truckId);

    }

    @Override
    public Truck findById(int truckId) throws EntityNotFoundException {
        log.info("find truck method started");
        return truckRepository
                .findById(truckId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(TruckMessage.NOT_FOUND.getKey())));
    }

    @Override
    public Truck getByPlate(String plate) {
        return truckRepository.findByPlate(plate);
    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
