package com.agendas.api.service;

import com.agendas.api.dto.ClienteDto;

import java.util.List;

public interface ClienteService {

    ClienteDto crear(ClienteDto dto);
    ClienteDto actualizar(ClienteDto dto);
    ClienteDto obtenerPorId(Long id);
    void eliminarPorId(Long id);
    List<ClienteDto> obtenerTodos();
}
