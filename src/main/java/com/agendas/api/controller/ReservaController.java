package com.agendas.api.controller;

import com.agendas.api.dto.HorarioLibreDto;
import com.agendas.api.dto.ReservaDto;
import com.agendas.api.dto.ReservaDto;
import com.agendas.api.service.ClienteService;
import com.agendas.api.service.ReservaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDto> guardar(@RequestBody ReservaDto dto) {
        log.info("REST - Request guardar : {}", dto);
        return ResponseEntity.ok(reservaService.crear(dto));
    }

    @PutMapping
    public ResponseEntity<ReservaDto> actualizar(@RequestBody ReservaDto dto) {
        log.info("REST - Request actualizar : {}", dto);
        return ResponseEntity.ok(reservaService.actualizar(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> obtenerPorId(@PathVariable("id") Long id) {
        log.info("REST - Request obtenerPorId : {}", id);
        return ResponseEntity.ok(reservaService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") Long id) {
        log.info("REST - Request eliminarPorId : {}", id);
        reservaService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDto>> obtenerTodos() {
        log.info("REST - Request obtenerTodos");
        return ResponseEntity.ok(reservaService.obtenerTodos());
    }

    @GetMapping("/reserva-por-dia/{fecha}")
    public ResponseEntity<List<ReservaDto>> buscarReservaPorDia(@PathVariable("fecha") LocalDateTime fecha) {
        log.info("REST - Request buscarReservaPorDia : {}", fecha);
        return ResponseEntity.ok(reservaService.buscarReservaPorDia(fecha));
    }

    @GetMapping("/buscar-horario-libre/{fecha}")
    public ResponseEntity<List<HorarioLibreDto>> buscarHorarioLibrePorDia(@PathVariable("fecha") LocalDateTime fecha) {
        log.info("REST - Request buscarHorarioLibrePorDia : {}", fecha);
        return ResponseEntity.ok(reservaService.buscarHorarioLibrePorDia(fecha));
    }
}
