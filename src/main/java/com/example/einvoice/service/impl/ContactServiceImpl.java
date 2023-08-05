package com.example.einvoice.service.impl;

import com.example.einvoice.core.constant.message.ContactMessage;
import com.example.einvoice.core.dto.ContactDto;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Setter
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final MessageSource messageSource;


    @Override
    public GeneralResult create(Contact contact) {
        log.info("contact create method started with request : " + contact);

        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);
        log.info("contact create method ended");
        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDto);
    }

    @Override
    @Transactional
    public GeneralResult update(UpdateContactRequest updateContactRequest, int contactId) throws EntityNotFoundException {
        log.info("contact update method started with request : " + updateContactRequest);
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));
        setUpdateContactRequestToEntity(updateContactRequest, contact);
        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);
        log.info("contact update method ended");
        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDto);
    }

    @Override
    public GeneralResult getAll() {
        log.info("get contact method started" );
        List<Contact> contactList = contactRepository.findAll();
        List<ContactDto> contactDtoList = contactList
                .stream()
                .map(ContactMapper.MAPPER::entityToResponse)
                .toList();
        log.info("get contact method ended");
        return new DataResult<>(getMessage(ContactMessage.SUCCESSFUL.getKey()), true, contactDtoList);
    }

    @Override
    @Transactional
    public void deleteByID(int contactId) throws EntityNotFoundException {
        log.info("delete contact method started" );
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));

        contactRepository.delete(contact);
        log.info("delete contact method ended");
    }

    @Override
    public void deleteByID(Contact contact) throws EntityNotFoundException {
        log.info("delete contact method started" );
        Contact contact1 = contactRepository
                .findById(contact.getId())
                .orElseThrow(() -> new EntityNotFoundException(getMessage((ContactMessage.NOT_FOUND.getKey()))));
        contactRepository.delete(contact1);
        log.info("delete contact method ended");
    }

    @Override
    public boolean existsById(int contactId) {
        return contactRepository.existsById(contactId);
    }

    @Override
    public Contact findById(int contactId) throws EntityNotFoundException {
        log.info("find contact method started" );
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
