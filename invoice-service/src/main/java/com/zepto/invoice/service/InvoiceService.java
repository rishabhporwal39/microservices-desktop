package com.zepto.invoice.service;

import com.zepto.invoice.client.CategoryClient;
import com.zepto.invoice.dto.request.InvoiceRequest;
import com.zepto.invoice.dto.response.CategoryResponse;
import com.zepto.invoice.dto.response.InvoiceResponse;
import com.zepto.invoice.entity.InvoiceEntity;
import com.zepto.invoice.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;


    @Autowired
    CategoryClient categoryClient;


    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    public void createInvoice(InvoiceRequest invoiceRequest) {
        
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setCustomerName(invoiceRequest.getCustomerName());
        invoiceEntity.setGst(invoiceRequest.getGst());

        invoiceEntity.setInvId("xyz");
        invoiceEntity.setStatus("active");

        CategoryResponse categoryResponse = categoryClient.getCategoryById(1L);
        System.out.println("Received response from 'product-category' service, " + categoryResponse.getName());

        invoiceEntity = invoiceRepository.save(invoiceEntity);

        System.out.println(invoiceEntity);

    }


    @Cacheable(value = "invoices", key = "#page + '-' + #size")
    public List<InvoiceResponse> getInvoices(int page, int size) {

        log.info("Fetching invoices - page: {}, size: {}", page, size);
        log.debug("Fetching invoices from database...");

        Pageable pageable = PageRequest.of(page, size);
        Page<InvoiceEntity> result = invoiceRepository.findAll(pageable);

        log.info("Fetched {} invoices from database for page: {}, size: {}",
                result.getNumberOfElements(), page, size);

        List<InvoiceResponse> response = new ArrayList<>();
        for (InvoiceEntity invoiceEntity  : result) {
            response.add(entityToResponse(invoiceEntity));
        }
        log.info("Successfully prepared {} invoice responses", response.size());
        return response;
    }

    private InvoiceResponse entityToResponse(InvoiceEntity invoiceEntity) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setId(invoiceEntity.getId());
        invoiceResponse.setInvId(invoiceEntity.getInvId());
        invoiceResponse.setCustomerName(invoiceEntity.getCustomerName());
        invoiceResponse.setGst(invoiceEntity.getGst());
        invoiceResponse.setStatus(invoiceEntity.getStatus());
        return invoiceResponse;
    }
}
