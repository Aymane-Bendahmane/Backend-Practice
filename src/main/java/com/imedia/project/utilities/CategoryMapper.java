package com.imedia.project.utilities;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.entites.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDto categoryToDto(Category category);

    Category dtoToCategory(CategoryDto categoryDto);
}
