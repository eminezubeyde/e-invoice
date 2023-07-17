package com.example.einvoice.api.controller;

import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.requests.update.UpdateBonusRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bonus")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService bonusService;

    @PostMapping
    public GeneralResult create(@RequestBody CreateBonusRequest createBonusRequest) {
        return bonusService.create(createBonusRequest);
    }

    @PutMapping
    public GeneralResult update(@RequestBody UpdateBonusRequest updateBonusRequest, @RequestParam int bonusId) {
        return bonusService.update(updateBonusRequest, bonusId);
    }

    @DeleteMapping
    public void delete(@RequestParam int bonusId) {
        bonusService.delete(bonusId);
    }
}
