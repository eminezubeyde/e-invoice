package com.example.einvoice.service;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateCompanyRequest;
import com.example.einvoice.core.requests.update.UpdateCompanyRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Company;

public interface CompanyService {
    GeneralResult create(CreateCompanyRequest createCompanyRequest) throws AlreadyExistsException, EntityNotFoundException;

    GeneralResult update(UpdateCompanyRequest updateCompanyRequest, int companyId) throws EntityNotFoundException;

    GeneralResult getAll();

    void deleteById(int companyId) throws EntityNotFoundException;


    boolean existsById(int companyId);

    Company findById(int companyId) throws EntityNotFoundException;

    Company getByCompanyName(String companyName);
}
