package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.BonusRequest;
import com.example.einvoice.core.requests.CompanyRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.BonusService;
import com.example.einvoice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bonus")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService bonusService;

    @PostMapping
    public GeneralResult create(@RequestBody BonusRequest bonusRequest) {
        return bonusService.create(bonusRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody BonusRequest bonusRequest, @RequestParam int bonusId) {
        return bonusService.update(bonusRequest, bonusId);
    }

    @DeleteMapping
    public void delete(@RequestParam int bonusId) {
        bonusService.delete(bonusId);
    }
}
