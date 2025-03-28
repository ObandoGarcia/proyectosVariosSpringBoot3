package com.obando.product.service.services;


import com.obando.product.service.models.dtos.CreateProductRequest;
import com.obando.product.service.models.dtos.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse findById(Long id);
    List<ProductResponse> findAll();
    List<ProductResponse> findAllByCategoryId(Long categoryId);
    ProductResponse save(CreateProductRequest createProductRequest);
    ProductResponse update(Long id, CreateProductRequest createProductRequest);
    void deleteById(Long id);
}
