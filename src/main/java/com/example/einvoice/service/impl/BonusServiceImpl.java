package com.example.einvoice.service.impl;

import com.example.einvoice.core.dto.BonusDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.BonusMapper;
import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Bonus;
import com.example.einvoice.repository.BonusRepository;
import com.example.einvoice.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;

    @Override
    public GeneralResult create(CreateBonusRequest createBonusRequest) {



        return null;
    }

    @Override
    public GeneralResult getAll() {
        List<Bonus> bonuses = bonusRepository.findAll();
        List<BonusDto> bonusDtos = bonuses
                .stream().map(BonusMapper.MAPPER::entityToResponse)
                .toList();

        return new DataResult<>("Successful", bonusDtos);
    }

    @Override
    public void delete(int bonusId) throws EntityNotFoundException {
        Bonus bonus = bonusRepository
                .findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));

        bonusRepository.delete(bonus);
    }
}
