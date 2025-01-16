package com.agendas.api.service.impl;

import com.agendas.api.dto.HorarioLibreDto;
import com.agendas.api.dto.ReservaDto;
import com.agendas.api.dto.mapper.HorarioLibreMapper;
import com.agendas.api.dto.mapper.ReservaMapper;
import com.agendas.api.exception.ModeloNotFoundException;
import com.agendas.api.repository.ReservaRepository;
import com.agendas.api.service.ReservaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final HorarioLibreMapper horarioLibreMapper;

    @Override
    public ReservaDto crear(ReservaDto dto) {
        validExisteReserva(dto.getHoraInicio(), dto.getHoraFin());
        return reservaMapper.toDto( reservaRepository.save(reservaMapper.toEntity(dto)) );
    }

    @Override
    public ReservaDto actualizar(ReservaDto dto) {
        obtenerPorId(dto.getId());
        validExisteReserva(dto.getHoraInicio(), dto.getHoraFin());
        return reservaMapper.toDto( reservaRepository.save(reservaMapper.toEntity(dto)) );
    }

    @Override
    public ReservaDto obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .map(reservaMapper::toDto)
                .orElseThrow(() -> new ModeloNotFoundException("ID NO ENCONTRADO " + id));
    }

    @Override
    public void eliminarPorId(Long id) {
        obtenerPorId(id);
        reservaRepository.deleteById(id);
    }

    @Override
    public List<ReservaDto> obtenerTodos() {
        return reservaRepository.findAll().stream()
                .map(reservaMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservaDto> buscarReservaPorDia(LocalDateTime fecha) {

        LocalDateTime horaInicio = fecha
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        LocalDateTime horaFin = fecha
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);

        return reservaRepository.buscarReservaPorFecha(horaInicio, horaFin).stream()
                .map(reservaMapper::toDto)
                .toList();
    }

    @Override
    public List<HorarioLibreDto> buscarHorarioLibrePorDia(LocalDateTime fecha) {
        LocalDateTime horaInicio = fecha
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        LocalDateTime horaFin = fecha
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        LocalDateTime[] horarioLibre = {horaInicio};

        List<HorarioLibreDto> horariosLibres =  reservaRepository.buscarReservaPorFecha(horaInicio, horaFin).stream()
                .filter(x -> horarioLibre[0].isBefore(x.getHoraInicio()))
                .map(x -> HorarioLibreDto.builder()
                        .horaInicio(horarioLibre[0])
                        .horaFin(x.getHoraInicio())
                        .build())
                .peek(x -> horarioLibre[0] = x.getHoraFin())
                .collect(Collectors.toList());

        if (horarioLibre[0].isBefore(horaFin)) {
            horariosLibres.add(HorarioLibreDto.builder()
                    .horaInicio(horarioLibre[0])
                    .horaFin(horaFin)
                    .build());
        }

        return horariosLibres;
    }

    public List<ReservaDto> buscarReservaPorDia(LocalDateTime horaInicio, LocalDateTime horaFin) {
        return reservaRepository.buscarReservaPorFecha(horaInicio, horaFin).stream()
                .map(reservaMapper::toDto)
                .toList();
    }



    private void validExisteReserva(LocalDateTime horaInicio, LocalDateTime horaFin) {

        if( reservaRepository.existeReserva(horaInicio, horaFin) ) {
            throw new ModeloNotFoundException("Ya existe una reserva en la fecha especificada");
        }
    }
}
