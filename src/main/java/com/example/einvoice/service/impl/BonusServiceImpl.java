package com.example.einvoice.service.impl;

import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.requests.update.UpdateBonusRequest;
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
    public GeneralResult create(CreateBonusRequest createBonusRequest) {


        return null;
    }

    @Override
    public GeneralResult update(UpdateBonusRequest updateBonusRequest, int bonusId) {
        return null;
    }

    @Override
    public void delete(int bonusId) {

    }
}
