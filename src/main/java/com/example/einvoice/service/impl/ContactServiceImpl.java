package com.example.einvoice.service.impl;

import com.example.einvoice.config.MessageConfig;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.message.CompanyMessage;
import com.example.einvoice.core.message.ContactMessage;
import com.example.einvoice.core.dto.ContactDto;
import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.ContactService;
import com.example.einvoice.service.DriverService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final MessageSource messageSource;
    private final DriverService driverService;
    private final CompanyService companyService;

    @Override
    public GeneralResult create(Contact contact) {

        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);
        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDto);
    }

    @Override
    @Transactional
    public GeneralResult update(UpdateContactRequest updateContactRequest, int contactId) {
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));
        setUpdateContactRequestToEntity(updateContactRequest, contact);
        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);

        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDto);
    }

    @Override
    public GeneralResult getAll() {
        List<Contact> contactList = contactRepository.findAll();
        List<ContactDto> contactDtoList = contactList
                .stream()
                .map(ContactMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDtoList);
    }

    @Override
    @Transactional
    public void deleteByID(int contactId) {
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));


        //Todo contact silerken company ya da driver hatası veriyor
        contactRepository.delete(contact);

    }

    @Override
    public void deleteByID(Contact contact) {
        Contact contact1 = contactRepository
                .findById(contact.getId())
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));
        contactRepository.delete(contact1);
    }

    @Override
    public boolean existsById(int contactId) {
        return contactRepository.existsById(contactId);
    }

    @Override
    public Contact findById(int contactId) {
        return contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));

    }

    private void setUpdateContactRequestToEntity(UpdateContactRequest updateContactRequest, Contact contact) {
        contact.setAddress(updateContactRequest.getAddress());
        contact.setTelephoneNumber(updateContactRequest.getTelephoneNumber());
    }

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
