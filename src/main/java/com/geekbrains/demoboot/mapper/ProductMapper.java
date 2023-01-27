package com.geekbrains.demoboot.mapper;

import com.geekbrains.demoboot.dto.ProductDto;
import com.geekbrains.demoboot.entities.Product;
import org.mapstruct.Mapper;

/**
 * ProductMapper
 *
 */
@Mapper(config = BaseMapperConfig.class)
public interface ProductMapper {
    ProductDto toDto(Product model);

    Product toModel(ProductDto dto);
}
