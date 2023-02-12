package com.imedia.project.mappers;

import com.imedia.project.dto.ConversionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConversionMapper {
    ConversionMapper INSTANCE = Mappers.getMapper( ConversionMapper.class );
    ConversionDto objectToDto(Object o);
}
