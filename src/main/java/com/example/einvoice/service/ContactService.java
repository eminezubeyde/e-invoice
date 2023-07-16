package com.example.einvoice.service;

import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Contact;

public interface ContactService {
    GeneralResult create(ContactRequest contactRequest);

    GeneralResult update(ContactRequest contactRequest, int contactId);
    GeneralResult getAll();

    void delete(int contactId);

    boolean existsById(int contactId);

    Contact findById(int contactId);

}
