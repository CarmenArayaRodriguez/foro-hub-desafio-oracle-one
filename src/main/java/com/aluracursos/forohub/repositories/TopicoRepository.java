package com.aluracursos.forohub.repositories;

import com.aluracursos.forohub.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findByCursoAndFechaCreacionBetween(String curso, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
