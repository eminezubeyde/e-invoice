package com.example.einvoice.service;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface CompanyService {
    GeneralResult create(CompanyRequest companyRequest);

    GeneralResult update(CompanyRequest companyRequest, int companyId);

    void delete(int companyId);
}
