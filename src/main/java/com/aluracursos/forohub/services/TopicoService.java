package com.aluracursos.forohub.services;

import com.aluracursos.forohub.models.Topico;
import com.aluracursos.forohub.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> findAll() {
        return topicoRepository.findAll();
    }

    public Topico save(Topico topico) {
        return topicoRepository.save(topico);
    }
}


















