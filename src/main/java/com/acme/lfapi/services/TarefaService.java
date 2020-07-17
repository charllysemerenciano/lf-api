package com.acme.lfapi.services;

import com.acme.lfapi.entities.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TarefaService {
    /**
     * Persiste uma tarefa na base de dados.
     *
     * @param tarefa
     * @return Tarefa
     */
    Tarefa persistir(Tarefa tarefa);

    /**
     * Busca e retorna um tarefa dado um CPF.
     *
     * @param id
     * @param pageRequest
     * @return Optional<ListaTarefa>
     */
    Page<Tarefa> buscarPorListaId(Long id, PageRequest pageRequest);


    /**
     * Busca e retorna um tarefa por ID.
     *
     * @param id
     * @return Optional<Tarefa>
     */
    Optional<Tarefa> buscarPorId(Long id);


    /**
     * Remove um lançamento da base de dados.
     *
     * @param id
     */
    void remover(Long id);
}
