package com.example.einvoice.api.controller;

import com.example.einvoice.core.exception.EntityNotFoundException;
import com.example.einvoice.core.requests.create.CreateBonusRequest;
import com.example.einvoice.core.result.GeneralResult;
import com.example.einvoice.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bonus")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService bonusService;

    @GetMapping
    public GeneralResult getAll() {
        return bonusService.getAll();
    }

    @DeleteMapping
    public void delete(@RequestParam int bonusId) throws EntityNotFoundException {
        bonusService.delete(bonusId);
    }
}
