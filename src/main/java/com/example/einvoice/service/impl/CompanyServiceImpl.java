package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.CompanyMapper;
import com.example.einvoice.core.message.CompanyMessage;
import com.example.einvoice.core.message.ContactMessage;
import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.responses.CompanyResponse;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Company;
import com.example.einvoice.model.Contact;
import com.example.einvoice.repository.CompanyRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.ContactService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ContactService contactService;

    @Override
    public GeneralResult create(CompanyRequest companyRequest,int contactId) throws AlreadyExistsException, EntityNotFoundException {
        if (companyRepository.existsByTaxNumber(companyRequest.getTaxNumber())) {
            throw new AlreadyExistsException(CompanyMessage.ALREADY_EXISTS.toString());
        }
        if (!contactService.existsById(companyRequest.getContactId())) {
            throw new EntityNotFoundException(ContactMessage.NOT_FOUND.toString());
        }
        Company company = CompanyMapper.MAPPER.requestToEntity(companyRequest);
        companyRepository.save(company);
        CompanyResponse companyResponse = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(companyResponse);
    }

    @Override
    @Transactional
    public GeneralResult update(CompanyRequest companyRequest, int companyId) throws EntityNotFoundException {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString()));
        setUpdateCompanyRequestToEntity(companyRequest, company);
        companyRepository.save(company);
        CompanyResponse companyResponse = CompanyMapper.MAPPER.entityToResponse(company);
        return new DataResult<>(companyResponse);
    }
    @Override
    public GeneralResult getAll() {
        List<Company> companyList=companyRepository.findAll();
        List<CompanyResponse> companyResponseList=companyList
                .stream()
                .map(CompanyMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(companyResponseList);
    }

    @Override
    @Transactional
    public void delete(int companyId) throws EntityNotFoundException {
        if(!companyRepository.existsById(companyId)){
            throw new EntityNotFoundException(CompanyMessage.NOT_FOUND.toString());
        }
        companyRepository.deleteById(companyId);

    }


    private void setUpdateCompanyRequestToEntity(CompanyRequest companyRequest, Company company) {
        Contact contact = contactService.findById(companyRequest.getContactId());
        company.setContact(contact);
        company.setName(companyRequest.getName());
        company.setTaxNumber(companyRequest.getTaxNumber());

    }

}
