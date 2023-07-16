package com.example.einvoice.service.impl;

import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.repository.ContactRepository;
import com.example.einvoice.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    @Override
    public GeneralResult create(ContactRequest contactRequest) {
        return null;
    }

    @Override
    public GeneralResult update(ContactRequest contactRequest, int contactId) {
        return null;
    }

    @Override
    public void delete(int companyId) {

    }
}
