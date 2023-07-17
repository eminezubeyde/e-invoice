package com.example.einvoice.service.impl;

import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.message.ContactMessage;
import com.example.einvoice.core.requests.create.CreateContactRequest;
import com.example.einvoice.core.dto.ContactDto;
import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Contact;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public GeneralResult create(Contact contact) {//Todo diğer taraftan gelen entity buraya nasıl kaydedilecek

        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);
        return new DataResult<>(contactDto);
    }

    @Override
    @Transactional
    public GeneralResult update(UpdateContactRequest updateContactRequest, int contactId) {
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(ContactMessage.NOT_FOUND.toString()));
        setUpdateContactRequestToEntity(updateContactRequest, contact);
        contactRepository.save(contact);
        ContactDto contactDto = ContactMapper.MAPPER.entityToResponse(contact);

        return new DataResult<>(contactDto);
    }

    @Override
    public GeneralResult getAll() {
        List<Contact> contactList = contactRepository.findAll();
        List<ContactDto> contactDtoList = contactList
                .stream()
                .map(ContactMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(contactDtoList);
    }

    @Override
    @Transactional
    public void delete(int contactId) {
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(ContactMessage.NOT_FOUND.toString()));

        //Todo contact silerken company ya da driver hatası veriyor
        contactRepository.delete(contact);

    }

    @Override
    public boolean existsById(int contactId) {
        return contactRepository.existsById(contactId);
    }

    @Override
    public Contact findById(int contactId) {
        return contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(ContactMessage.NOT_FOUND.toString()));

    }

    private void setUpdateContactRequestToEntity(UpdateContactRequest updateContactRequest, Contact contact) {
        contact.setAddress(updateContactRequest.getAddress());
        contact.setTelephoneNumber(updateContactRequest.getTelephoneNumber());
    }
}
