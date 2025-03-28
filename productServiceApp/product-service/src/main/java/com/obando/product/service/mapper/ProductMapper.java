package com.obando.product.service.mapper;

import com.obando.product.service.models.dtos.ProductResponse;
import com.obando.product.service.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(target = "status", expression = "java(mapStatus(product))")
    ProductResponse toProductResponse(Product product);

    default String mapStatus(Product product){
        return product.getStatus() ? "ACTIVE" : "INACTIVE";
    }
}
