package com.agendas.api.dto.mapper;

import com.agendas.api.dto.ReservaDto;
import com.agendas.api.entity.Reserva;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(source = "clienteId", target = "cliente.id")
    Reserva toEntity(ReservaDto dto);

    @InheritInverseConfiguration
    ReservaDto toDto(Reserva entity);
}
