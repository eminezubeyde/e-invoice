package com.example.einvoice.core.dto;

import com.example.einvoice.entity.Bonus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class DriverDto {
    private int id;
    private int truckId;
    private int contactId;
    private String name;
    private String surname;
    private String identityNumber;
    private BigDecimal salary;
    private List<BonusDto> bonuses;
}
