package com.agendas.api.dto.mapper;

import com.agendas.api.dto.ClienteDto;
import com.agendas.api.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDto dto);
    ClienteDto toDto(Cliente entity);
}
