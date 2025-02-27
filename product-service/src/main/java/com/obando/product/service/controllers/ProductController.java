package com.obando.product.service.controllers;

import com.obando.product.service.models.dtos.CreateProductRequest;
import com.obando.product.service.models.dtos.ProductResponse;
import com.obando.product.service.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponse> findAllByCategoryId(@PathVariable Long id){
        return productService.findAllByCategoryId(id);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody CreateProductRequest request){
        ProductResponse productResponse = productService.save(request);
        return ResponseEntity
                .created(URI.create("/api/products/" + productResponse.getId()))
                .body(productResponse);
    }

    @PutMapping("{id}")
    public ProductResponse update(@PathVariable Long id, @Valid @RequestBody CreateProductRequest request){
        return productService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
