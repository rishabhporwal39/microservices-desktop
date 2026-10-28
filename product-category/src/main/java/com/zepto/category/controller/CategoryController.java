package com.zepto.category.controller;

import com.zepto.category.dto.request.CategoryRequest;
import com.zepto.category.dto.response.CategoryResponse;
import com.zepto.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest)
    {
        CategoryResponse response = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("showCategories")
    public String listAllCategories()
    {
        categoryService.showAllCategories();
        return "SUCCESS";
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(response);
    }
}
