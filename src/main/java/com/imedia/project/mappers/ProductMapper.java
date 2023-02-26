package com.imedia.project.mappers;


import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductDto productToDto(Product product);
    Product dtoToProduct(ProductDto productDto);
    List<ProductDto> DTO_LIST(List<Product> products);
}
