package com.agendas.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;

}