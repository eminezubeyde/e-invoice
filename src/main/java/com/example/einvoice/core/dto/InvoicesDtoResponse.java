package com.example.einvoice.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class InvoicesDtoResponse {
    private List<InvoiceDto> invoiceDtos;
    private BigDecimal totalAmount;
    private int currentPage;
    private int totalPage;
    private int invoiceCount;

}
// toplamda 100 fatura var

// pagebale bir yapıda 20 tane dönüyorum
// 2. sayfa 20-40