package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface BonusService {
    GeneralResult create(CreateBonusRequest createBonusRequest);
    GeneralResult getAll();

    void delete(int bonusId) throws EntityNotFoundException;



}
