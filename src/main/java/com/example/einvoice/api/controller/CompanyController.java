package com.example.einvoice.api.controller;

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
    public GeneralResult create(@RequestBody CompanyRequest companyRequest) {
        return companyService.create(companyRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody CompanyRequest companyRequest, @RequestParam int companyId) {
        return companyService.update(companyRequest, companyId);
    }

    @DeleteMapping
    public void delete(@RequestParam int companyId) {
        companyService.delete(companyId);
    }
}
