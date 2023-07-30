package com.example.einvoice.service;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;

public interface ContactService {
    GeneralResult create(Contact contact);

    GeneralResult update(UpdateContactRequest updateContactRequest, int contactId) throws EntityNotFoundException;
    GeneralResult getAll();

    void deleteByID(int contactId) throws EntityNotFoundException;
    void deleteByID(Contact contact) throws EntityNotFoundException;

    boolean existsById(int contactId);

    Contact findById(int contactId) throws EntityNotFoundException;

}
