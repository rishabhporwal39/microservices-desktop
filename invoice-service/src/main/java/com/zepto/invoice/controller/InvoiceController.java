package com.zepto.invoice.controller;

import com.zepto.invoice.dto.request.InvoiceRequest;
import com.zepto.invoice.dto.response.InvoiceResponse;
import com.zepto.invoice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invoice")
@EnableFeignClients
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    @PostMapping("createInvoice")
    public void createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        log.info("Received request to create the invoice for customer: {} ", invoiceRequest.getCustomerName());
        invoiceService.createInvoice(invoiceRequest);
        log.info("Invoice creation request completely successful for customer: {}", invoiceRequest.getCustomerName());
        //return ResponseEntity.status(HttpStatus.ACCEPTED).body(res)
    }

    @GetMapping("listInvoices")
    public ResponseEntity<List<InvoiceResponse>> getResponses(@RequestParam int page,
                                                             @RequestParam int size) {
        log.info("Received request to fetch invoices - page: {}, size: {}", page, size);
        List<InvoiceResponse> response = invoiceService.getInvoices(page, size);
        log.info("Invoice list request completed successfully - returned {} invoices", response.size());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
