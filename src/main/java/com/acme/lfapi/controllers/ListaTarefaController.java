package com.acme.lfapi.controllers;

import com.acme.lfapi.dtos.ListaTarefaDto;
import com.acme.lfapi.entities.ListaTarefa;
import com.acme.lfapi.repositories.ListaTarefaRepository;
import com.acme.lfapi.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/listas")
@CrossOrigin(origins = "*")
public class ListaTarefaController {
    private static final Logger log = LoggerFactory.getLogger(ListaTarefaController.class);


    @Autowired
    private ListaTarefaRepository listaTarefaRepository;

    public ListaTarefaController() {
    }


    /**
     * Retorna uma lista dado um id.
     *
     * @param id
     * @return ResponseEntity<Response<ListaTarefaDto>>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<ListaTarefaDto>> buscarPorId(@PathVariable("id") int id) {
        Response<ListaTarefaDto> response = new Response<>();
        Optional<ListaTarefa> listaTarefa = listaTarefaRepository.findById((long) id);

        if (!listaTarefa.isPresent()) {
            log.info("Lista não encontrada para ID: {}", id);
            response.getErrors().add("Lista não encontrada para ID " + id);
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.converterListaTarefaDto(listaTarefa.get()));
        return ResponseEntity.ok(response);
    }

    /**
     * Popula um DTO com os dados de uma lista.
     *
     * @param listaTarefa
     * @return ListaTarefaDto
     */
    private ListaTarefaDto converterListaTarefaDto(ListaTarefa listaTarefa) {
        ListaTarefaDto listaTarefaDto = new ListaTarefaDto();
        listaTarefaDto.setId(listaTarefa.getId());
        listaTarefaDto.setNomeLista(listaTarefa.getNomeLista());
        listaTarefaDto.setStatus(listaTarefa.getStatus());

        return listaTarefaDto;
    }


}
