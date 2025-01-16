package com.agendas.api.service;

import com.agendas.api.dto.HorarioLibreDto;
import com.agendas.api.dto.ReservaDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaService {

    ReservaDto crear(ReservaDto dto);
    ReservaDto actualizar(ReservaDto dto);
    ReservaDto obtenerPorId(Long id);
    void eliminarPorId(Long id);
    List<ReservaDto> obtenerTodos();
    List<ReservaDto> buscarReservaPorDia(LocalDateTime fecha);
    List<HorarioLibreDto> buscarHorarioLibrePorDia(LocalDateTime fecha);
}
