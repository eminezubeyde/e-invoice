package com.example.einvoice.service;

import com.example.einvoice.core.requests.BonusRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface BonusService {
    GeneralResult create(BonusRequest bonusRequest);

    GeneralResult update(BonusRequest bonusRequest, int bonusId);

    void delete(int bonusId);
}
