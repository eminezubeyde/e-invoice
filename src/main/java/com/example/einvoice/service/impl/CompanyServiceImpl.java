package com.example.einvoice.service.impl;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.CompanyRepository;
import com.example.einvoice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    @Override
    public GeneralResult create(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public GeneralResult update(CompanyRequest companyRequest, int companyId) {
        return null;
    }

    @Override
    public void delete(int companyId) {

    }
}
