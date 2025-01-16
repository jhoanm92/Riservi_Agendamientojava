package com.agendas.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto {

    private Long id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Long clienteId;
    private EstadoDto estado;

}