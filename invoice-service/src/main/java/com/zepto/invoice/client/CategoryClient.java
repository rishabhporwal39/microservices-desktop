package com.zepto.invoice.client;

import com.zepto.invoice.dto.response.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "product-category")
public interface CategoryClient {

    @GetMapping("/category/{id}")
    CategoryResponse getCategoryById(@PathVariable("id") Long id);
}
