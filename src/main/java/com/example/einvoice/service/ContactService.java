package com.example.einvoice.service;

import com.example.einvoice.core.requests.create.CreateContactRequest;
import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.model.Contact;

public interface ContactService {
    GeneralResult create(CreateContactRequest createContactRequest);

    GeneralResult update(UpdateContactRequest updateContactRequest, int contactId);
    GeneralResult getAll();

    void delete(int contactId);

    boolean existsById(int contactId);

    Contact findById(int contactId);

}
