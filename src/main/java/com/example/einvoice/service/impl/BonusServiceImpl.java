package com.example.einvoice.service.impl;

import com.example.einvoice.core.constant.message.DriverMessage;
import com.example.einvoice.core.dto.BonusDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.BonusMapper;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Bonus;
import com.example.einvoice.repository.BonusRepository;
import com.example.einvoice.service.BonusService;
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
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;
    private final MessageSource messageSource;

    @Override
    public GeneralResult create(Bonus bonus) {
        log.info("bonus create method started with request : " + bonus);
        bonusRepository.save(bonus);
        BonusDto bonusDto = BonusMapper.MAPPER.entityToResponse(bonus);

        log.info("bonus create method ended");
        return new DataResult<>(getMessage(DriverMessage.SUCCESSFUL.getKey()), true, bonusDto);
    }

    @Override
    public GeneralResult getAll() {
        log.info("getAll bonus method started");
        List<Bonus> bonuses = bonusRepository.findAll();
        List<BonusDto> bonusDtos = bonuses
                .stream().map(BonusMapper.MAPPER::entityToResponse)
                .toList();
        log.info("bonus getAll method ended");
        return new DataResult<>("Successful", bonusDtos);
    }

    @Override
    public void delete(int bonusId) throws EntityNotFoundException {
        log.info("delete bonus method started");
        Bonus bonus = bonusRepository
                .findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        log.info("bonus delete method ended");
        bonusRepository.delete(bonus);
    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
