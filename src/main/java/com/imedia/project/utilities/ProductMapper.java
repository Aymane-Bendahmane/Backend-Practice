package com.imedia.project.utilities;


import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductDto productToDto(Product product);
    Product dtoToProduct(ProductDto productDto);
}
