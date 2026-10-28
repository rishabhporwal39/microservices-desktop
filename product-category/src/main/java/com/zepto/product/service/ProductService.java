package com.zepto.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zepto.category.entity.CategoryEntity;
import com.zepto.category.repository.CategoryRepository;
import com.zepto.product.entity.ProductEntity;
import com.zepto.product.repository.ProductRepository;
import com.zepto.product.request.response.ProductRequest;
import com.zepto.product.request.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		String categoryId = productRequest.getCategoryId();

		CategoryEntity parentEntity = categoryRepository.findById(Long.valueOf(categoryId)).get();

		ProductEntity childEntity = new ProductEntity();

		childEntity.setCategory(parentEntity);
		childEntity.setName(productRequest.getName());
		childEntity.setDescription(productRequest.getDescription());
		childEntity.setPrice(productRequest.getPrice());
		childEntity.setStatus("Active");

		ProductEntity responseEntity = productRepository.save(childEntity);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setCaregoryName(responseEntity.getCategory().getName());
		productResponse.setDescription(responseEntity.getDescription());
		productResponse.setStatus(responseEntity.getStatus());
		productResponse.setPrice(responseEntity.getPrice());

		return productResponse;
	}

}
