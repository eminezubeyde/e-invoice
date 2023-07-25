package com.example.einvoice.service;

import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.entity.Contact;

public interface ContactService {
    GeneralResult create(Contact contact);

    GeneralResult update(UpdateContactRequest updateContactRequest, int contactId);
    GeneralResult getAll();

    void deleteByID(int contactId);
    void deleteByID(Contact contact);

    boolean existsById(int contactId);

    Contact findById(int contactId);

}
