package com.agendas.api.controller;

import com.agendas.api.dto.ClienteDto;
import com.agendas.api.exception.ModeloNotFoundException;
import com.agendas.api.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> guardar(@RequestBody ClienteDto dto) {
        log.info("REST - Request guardar : {}", dto);
        return ResponseEntity.ok(clienteService.crear(dto));
    }

    @PutMapping
    public ResponseEntity<ClienteDto> actualizar(@RequestBody ClienteDto dto) {
        log.info("REST - Request actualizar : {}", dto);
        return ResponseEntity.ok(clienteService.actualizar(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable("id") Long id) {
        log.info("REST - Request obtenerPorId : {}", id);
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Long id) {
        log.info("REST - Request eliminarPorId : {}", id);
        clienteService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerTodos() {
        log.info("REST - Request obtenerTodos");
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }
}
