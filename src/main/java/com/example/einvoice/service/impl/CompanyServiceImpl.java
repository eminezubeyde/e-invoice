package com.example.einvoice.service.impl;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.CompanyMapper;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.constant.message.CompanyMessage;
import com.example.einvoice.core.requests.create.CreateCompanyRequest;
import com.example.einvoice.core.dto.CompanyDto;
import com.example.einvoice.core.requests.update.UpdateCompanyRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Company;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.repository.CompanyRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private ContactService contactService;
    private final MessageSource messageSource;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public GeneralResult create(CreateCompanyRequest createCompanyRequest) throws AlreadyExistsException, EntityNotFoundException {
        log.info("company create method started with request : " + createCompanyRequest);
        if (companyRepository.existsByTaxNumber(createCompanyRequest.getTaxNumber())) {
            log.error(" company already exists");
            throw new AlreadyExistsException(getMessage(CompanyMessage.ALREADY_EXISTS.getKey()));
        }
        if (companyRepository.existsByName(createCompanyRequest.getName())) {
            log.error(" company already exists");
            throw new AlreadyExistsException(getMessage(CompanyMessage.ALREADY_EXISTS.getKey()));
        }

        Company company = CompanyMapper.MAPPER.requestToEntity(createCompanyRequest);
        Contact contact = ContactMapper.MAPPER.requestToEntity(createCompanyRequest.getContactRequest());
        company.setContact(contact);
        contactService.create(contact);
        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        log.info("company create method ended");
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()), true, companyDto);

    }

    @Override
    @Transactional
    public GeneralResult update(UpdateCompanyRequest updateCompanyRequest, int companyId) throws EntityNotFoundException {
        log.info("company update method started with request : " + updateCompanyRequest);
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));

        company.setName(updateCompanyRequest.getName());
        company.setTaxNumber(updateCompanyRequest.getTaxNumber());

        companyRepository.save(company);
        CompanyDto companyDto = CompanyMapper.MAPPER.entityToResponse(company);
        log.info("company update method ended");
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()), true, companyDto);
    }

    @Override
    public GeneralResult getAll() {
        log.info("getAll company method started");
        List<Company> companyList = companyRepository.findAll();
        List<CompanyDto> companyDtoList = companyList
                .stream()
                .map(CompanyMapper.MAPPER::entityToResponse)
                .toList();
        log.info("getAll company method ended");
        return new DataResult<>(getMessage(CompanyMessage.SUCCESSFUL.getKey()), true, companyDtoList);
    }

    @Override
    @Transactional
    public void deleteById(int companyId) throws EntityNotFoundException {
        log.info("delete company method started");
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));
        Contact contact = company.getContact();
        contactService.deleteByID(contact);
        companyRepository.deleteById(companyId);
        log.info("company delete method ended");

    }


    @Override
    public boolean existsById(int companyId) {
        return companyRepository.existsById(companyId);
    }

    @Override
    public Company findById(int companyId) throws EntityNotFoundException {

        return companyRepository
                .findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage(CompanyMessage.NOT_FOUND.getKey())));
    }

    @Override
    public Company getByCompanyName(String companyName) {
        return companyRepository.findByName(companyName);
    }


    private String getMessage(String key, String... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, new Object[]{args}, locale);
    }

}
