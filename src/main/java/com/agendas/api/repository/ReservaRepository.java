package com.agendas.api.repository;

import com.agendas.api.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
            SELECT r
            FROM Reserva r
            WHERE (r.horaInicio < :horaFin AND r.horaFin > :horaInicio)
            ORDER BY r.horaInicio ASC""")
    List<Reserva> buscarReservaPorFecha(@Param("horaInicio")LocalDateTime horaInicio,
                                        @Param("horaFin")LocalDateTime horaFin);
    
    @Query("""
            SELECT (COUNT(r) > 0)
            FROM Reserva r
            WHERE (r.horaInicio < :horaFin AND r.horaFin > :horaInicio)""")
    boolean existeReserva(@Param("horaInicio") LocalDateTime horaInicio,
                          @Param("horaFin") LocalDateTime horaFin);
}