package com.example.einvoice.service;

import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.result.GeneralResult;

public interface ContactService {
    GeneralResult create(ContactRequest contactRequest);

    GeneralResult update(ContactRequest contactRequest, int contactId);

    void delete(int companyId);

}
