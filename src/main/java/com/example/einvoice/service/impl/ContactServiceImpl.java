package com.example.einvoice.service.impl;

import com.example.einvoice.core.mapper.ContactMapper;
import com.example.einvoice.core.message.CompanyMessage;
import com.example.einvoice.core.message.ContactMessage;
import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.responses.ContactResponse;
import com.example.einvoice.core.result.DataResult;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Contact;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public GeneralResult create(ContactRequest contactRequest) {
        Contact contact = ContactMapper.MAPPER.requestToEntity(contactRequest);
        contactRepository.save(contact);
        ContactResponse contactResponse = ContactMapper.MAPPER.entityToResponse(contact);
        return new DataResult<>(contactResponse);
    }

    @Override
    @Transactional
    public GeneralResult update(ContactRequest contactRequest, int contactId) {
        Contact contact = contactRepository
                .findById(contactId)
                .orElseThrow(() -> new EntityNotFoundException(ContactMessage.NOT_FOUND.toString()));
        setUpdateContactRequestToEntity(contactRequest, contact);
        contactRepository.save(contact);
        ContactResponse contactResponse = ContactMapper.MAPPER.entityToResponse(contact);

        return new DataResult<>(contactResponse);
    }

    @Override
    public GeneralResult getAll() {
        List<Contact> contactList = contactRepository.findAll();
        List<ContactResponse> contactResponseList = contactList
                .stream()
                .map(ContactMapper.MAPPER::entityToResponse)
                .toList();
        return new DataResult<>(contactResponseList);
    }

    @Override
    @Transactional
    public void delete(int contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new EntityNotFoundException(ContactMessage.NOT_FOUND.toString());
        }
        contactRepository.deleteById(contactId);

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

    private void setUpdateContactRequestToEntity(ContactRequest contactRequest, Contact contact) {
        contact.setAddress(contactRequest.getAddress());
        contact.setTelephoneNumber(contactRequest.getTelephoneNumber());
    }
}
