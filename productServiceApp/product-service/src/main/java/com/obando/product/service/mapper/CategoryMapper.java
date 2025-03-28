package com.obando.product.service.mapper;

import com.obando.product.service.models.dtos.CategoryResponse;
import com.obando.product.service.models.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}
