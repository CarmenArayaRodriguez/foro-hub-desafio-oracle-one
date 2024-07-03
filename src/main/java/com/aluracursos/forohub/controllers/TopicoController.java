package com.aluracursos.forohub.controllers;

import com.aluracursos.forohub.models.Topico;
import com.aluracursos.forohub.repositories.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<Topico> crearTopico(@Valid @RequestBody Topico topico) {
        boolean exists = topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje());
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict
        }
        Topico savedTopico = topicoRepository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTopico); // 201 Created
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fechaCreacion") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Topico> topicos;

        if (curso != null && anio != null) {
            LocalDate startDate = LocalDate.of(anio, 1, 1);
            LocalDate endDate = LocalDate.of(anio, 12, 31);
            topicos = topicoRepository.findByCursoAndFechaCreacionBetween(curso, startDate.atStartOfDay(), endDate.atTime(23, 59, 59), pageable);
        } else {
            topicos = topicoRepository.findAll(pageable);
        }

        return ResponseEntity.ok(topicos.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> detallarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            return ResponseEntity.ok(topicoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoActualizado) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            Topico topico = topicoOpt.get();

            // Verificar si el título y el mensaje son únicos
            boolean exists = topicoRepository.existsByTituloAndMensaje(topicoActualizado.getTitulo(), topicoActualizado.getMensaje());
            if (exists && !(topico.getTitulo().equals(topicoActualizado.getTitulo()) && topico.getMensaje().equals(topicoActualizado.getMensaje()))) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
            }

            // Actualizar los campos
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            topico.setStatus(topicoActualizado.getStatus());
            topico.setAutor(topicoActualizado.getAutor());
            topico.setCurso(topicoActualizado.getCurso());
            Topico topicoGuardado = topicoRepository.save(topico);

            return ResponseEntity.ok(topicoGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        if (topicoOpt.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();  // Respuesta 200 OK si el tópico fue eliminado exitosamente
        } else {
            return ResponseEntity.notFound().build();  // Respuesta 404 Not Found si el tópico no existe
        }
    }
}
