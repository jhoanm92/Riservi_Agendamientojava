package com.agendas.api.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDto {

    private Long id;
    private String descripcion;

}