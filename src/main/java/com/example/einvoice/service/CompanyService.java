package com.example.einvoice.service;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.DriverRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface CompanyService {
    GeneralResult create(CompanyRequest companyRequest,int contactId) throws AlreadyExistsException, EntityNotFoundException;

    GeneralResult update(CompanyRequest companyRequest, int companyId) throws EntityNotFoundException;

    GeneralResult getAll();

    void delete(int companyId) throws EntityNotFoundException;


}
