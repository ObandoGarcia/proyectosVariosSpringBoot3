package com.obando.product.service.services;

import com.obando.product.service.exceptions.CategoryNotFoundException;
import com.obando.product.service.exceptions.ProductNotFoundException;
import com.obando.product.service.mapper.ProductMapper;
import com.obando.product.service.models.dtos.CreateProductRequest;
import com.obando.product.service.models.dtos.ProductResponse;
import com.obando.product.service.models.entities.Product;
import com.obando.product.service.repositories.CategoryRepository;
import com.obando.product.service.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAllByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(productRepository::findAllByCategory)
                .map(products -> products.stream()
                        .map(productMapper::toProductResponse)
                        .collect(Collectors.toList()))
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public ProductResponse save(CreateProductRequest createProductRequest) {
        return categoryRepository.findById(createProductRequest.getCategoryId())
                .map(category -> {
                    Product product = new Product();
                    product.setName(createProductRequest.getName());
                    product.setDescription(createProductRequest.getDescription());
                    product.setPrice(createProductRequest.getPrice());
                    product.setCategory(category);
                    product.setStatus(Boolean.TRUE);
                    return productRepository.save(product);
                })
                .map(productMapper::toProductResponse)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public ProductResponse update(Long id, CreateProductRequest createProductRequest) {
        return productRepository.findById(id)
                .map(product -> categoryRepository
                        .findById(createProductRequest.getCategoryId())
                        .map(category -> {
                            product.setName(createProductRequest.getName());
                            product.setDescription(createProductRequest.getDescription());
                            product.setPrice(createProductRequest.getPrice());
                            product.setCategory(category);
                            return productRepository.save(product);
                        })
                        .orElseThrow(CategoryNotFoundException::new))
                .map(productMapper::toProductResponse)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException();
        }

        productRepository.deleteById(id);
    }
}
