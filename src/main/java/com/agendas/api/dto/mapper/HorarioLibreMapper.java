package com.agendas.api.dto.mapper;

import com.agendas.api.dto.HorarioLibreDto;
import com.agendas.api.entity.Reserva;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorarioLibreMapper {

    HorarioLibreDto toResponse(Reserva entity);
}
