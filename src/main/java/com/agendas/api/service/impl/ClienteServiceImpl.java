package com.agendas.api.service.impl;

import com.agendas.api.dto.ClienteDto;
import com.agendas.api.dto.mapper.ClienteMapper;
import com.agendas.api.exception.ModeloNotFoundException;
import com.agendas.api.repository.ClienteRepository;
import com.agendas.api.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDto crear(ClienteDto dto) {
        return clienteMapper.toDto( clienteRepository.save(clienteMapper.toEntity(dto)) );
    }

    @Override
    public ClienteDto actualizar(ClienteDto dto) {
        obtenerPorId(dto.getId());
        return clienteMapper.toDto( clienteRepository.save(clienteMapper.toEntity(dto)) );
    }

    @Override
    public ClienteDto obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new ModeloNotFoundException("ID NO ENCONTRADO " + id));
    }

    @Override
    public void eliminarPorId(Long id) {
        obtenerPorId(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteDto> obtenerTodos() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDto)
                .toList();
    }
}
