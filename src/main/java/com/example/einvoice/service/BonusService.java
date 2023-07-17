package com.example.einvoice.service;

import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.requests.update.UpdateBonusRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface BonusService {
    GeneralResult create(CreateBonusRequest createBonusRequest);

    GeneralResult update(UpdateBonusRequest updateBonusRequest, int bonusId);

    void delete(int bonusId);
}
