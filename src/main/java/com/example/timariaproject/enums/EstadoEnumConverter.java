package com.example.timariaproject.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoEnumConverter implements AttributeConverter<EstadoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EstadoEnum estadoEnum) {
        return estadoEnum != null ? estadoEnum.getId() : null;
    }

    @Override
    public EstadoEnum convertToEntityAttribute(Integer dbData) {
        return dbData != null ? EstadoEnum.fromId(dbData) : null;
    }
}
