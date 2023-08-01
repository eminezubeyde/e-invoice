package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Bonus;

public interface BonusService {
    GeneralResult create(Bonus bonus);
    GeneralResult getAll();

    void delete(int bonusId) throws EntityNotFoundException;



}
