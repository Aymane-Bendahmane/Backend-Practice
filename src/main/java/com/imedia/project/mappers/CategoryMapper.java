package com.imedia.project.mappers;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.entites.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );

    CategoryDto categoryToDto(Category category);

    Category dtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> DTO_LIST(List<Category> categories);

}
