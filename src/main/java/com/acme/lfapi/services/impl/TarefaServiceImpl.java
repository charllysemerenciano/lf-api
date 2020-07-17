package com.acme.lfapi.services.impl;

import com.acme.lfapi.entities.Tarefa;
import com.acme.lfapi.repositories.TarefaRepository;
import com.acme.lfapi.services.TarefaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaServiceImpl implements TarefaService {

    private static final Logger log = LoggerFactory.getLogger(TarefaServiceImpl.class);

    @Autowired
    private TarefaRepository tarefaRepository;



    @Override
    public Page<Tarefa> buscarPorListaId(Long id, PageRequest pageRequest) {
        log.info("Buscando tarefa por lista ID {}", id);
        return this.tarefaRepository.findByListaTarefaId(id, pageRequest);
    }

    @Cacheable("lancamentoPorId")
    public Optional<Tarefa> buscarPorId(Long id) {
        log.info("Buscando um lançamento pelo ID {}", id);
        return this.tarefaRepository.findById(id);
    }

    @CachePut("lancamentoPorId")
    public Tarefa persistir(Tarefa tarefa) {
        log.info("Persistindo a tarefa: {}", tarefa);
        return this.tarefaRepository.save(tarefa);
    }

    public void remover(Long id) {
        log.info("Removendo o lançamento ID {}", id);
        this.tarefaRepository.deleteById(id);
    }
}
