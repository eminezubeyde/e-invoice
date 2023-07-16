package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public GeneralResult create(@RequestBody CompanyRequest companyRequest,@RequestParam int contactId) throws AlreadyExistsException, EntityNotFoundException {
        return companyService.create(companyRequest,contactId);
    }

    @PutMapping
    public GeneralResult update(@RequestBody CompanyRequest companyRequest, @RequestParam int companyId) throws EntityNotFoundException {
        return companyService.update(companyRequest, companyId);
    }

    @DeleteMapping
    public void delete(@RequestParam int companyId) throws EntityNotFoundException {
        companyService.delete(companyId);
    }

    @GetMapping
    public GeneralResult getAll(){
       return companyService.getAll();
    }
}
