package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.requests.ContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.CompanyService;
import com.example.einvoice.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public GeneralResult create(@RequestBody ContactRequest contactRequest) {
        return contactService.create(contactRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody ContactRequest contactRequest, @RequestParam int contactId) {
        return contactService.update(contactRequest, contactId);
    }
    @GetMapping
    public GeneralResult getAll(){
        return contactService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestParam int contactId) {
        contactService.delete(contactId);
    }
}
