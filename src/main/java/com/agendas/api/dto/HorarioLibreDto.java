package com.agendas.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioLibreDto {

    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
}
