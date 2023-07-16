package com.example.einvoice.service.impl;

import com.example.einvoice.core.requests.BonusRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.BonusRepository;
import com.example.einvoice.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BonusServiceImpl implements BonusService {
    private final BonusRepository bonusRepository;
    @Override
    public GeneralResult create(BonusRequest bonusRequest) {


        return null;
    }

    @Override
    public GeneralResult update(BonusRequest bonusRequest, int bonusId) {
        return null;
    }

    @Override
    public void delete(int bonusId) {

    }
}
