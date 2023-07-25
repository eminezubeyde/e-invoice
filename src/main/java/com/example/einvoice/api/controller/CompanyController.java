package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.AlreadyExistsException;
import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateCompanyRequest;
import com.example.einvoice.core.requests.update.UpdateCompanyRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public GeneralResult create(@Valid @RequestBody CreateCompanyRequest createCompanyRequest) throws AlreadyExistsException, EntityNotFoundException {
        return companyService.create(createCompanyRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody UpdateCompanyRequest updateCompanyRequest, @RequestParam int companyId) throws EntityNotFoundException {
        return companyService.update(updateCompanyRequest, companyId);
    }

    @DeleteMapping
    public void delete(@RequestParam int companyId) throws EntityNotFoundException {
        companyService.deleteById(companyId);
    }

    @GetMapping
    public GeneralResult getAll(){
       return companyService.getAll();
    }
}
