package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.TruckMapper;
import com.example.einvoice.core.message.TruckMessage;
import com.example.einvoice.core.requests.create.CreateTruckRequest;
import com.example.einvoice.core.dto.TruckDto;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Truck;
import com.example.einvoice.repository.TruckRepository;
import com.example.einvoice.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TruckServiceImpl implements TruckService {
    private final TruckRepository truckRepository;
    @Override
    public GeneralResult create(CreateTruckRequest createTruckRequest) throws AlreadyExistsException {
        if(truckRepository.existsByPlate(createTruckRequest.getPlate())){
            throw new AlreadyExistsException(TruckMessage.ALREADY_EXISTS.toString());
        }
        Truck truck= TruckMapper.MAPPER.requestToEntity(createTruckRequest);
        truckRepository.save(truck);
        TruckDto truckResponse=TruckMapper.MAPPER.entityToResponse(truck);


        return new DataResult<>(truck);
    }


    @Override
    public GeneralResult getAll() {
        List<Truck> truckList=truckRepository.findAll();
        List<TruckDto>truckResponseList=truckList
                .stream()
                .map(TruckMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(truckResponseList);
    }

    @Override
    public void delete(int truckId) throws EntityNotFoundException {
        //TODO truck silerken driver olduğu için hata yiyor
        Truck truck=truckRepository
                .findById(truckId)
                .orElseThrow(()->new EntityNotFoundException(TruckMessage.NOT_FOUND.toString()));
        truckRepository.delete(truck);
    }

    @Override
    public boolean existsById(int truckId) {
       return truckRepository.existsById(truckId);

    }

    @Override
    public Truck findById(int truckId) throws EntityNotFoundException {
        return truckRepository
                .findById(truckId)
                .orElseThrow(()->new EntityNotFoundException(TruckMessage.NOT_FOUND.toString()));
    }

    @Override
    public Truck getByPlate(String plate) {
        return truckRepository.findByPlate(plate);
    }
}
