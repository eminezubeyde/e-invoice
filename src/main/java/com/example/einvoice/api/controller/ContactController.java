package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.update.UpdateContactRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PutMapping
    public GeneralResult update(@RequestBody UpdateContactRequest updateContactRequest, @RequestParam int contactId) {
        return contactService.update(updateContactRequest, contactId);
    }
    @GetMapping
    public GeneralResult getAll(){
        return contactService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestParam int contactId) {
        contactService.deleteByID(contactId);
    }
}
