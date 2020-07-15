package com.acme.lfapi.entities;


import com.acme.lfapi.enums.StatusTarefa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 5754246207015712518L;

    private Long id;

    private String tarefa;

    private Date dataCriacao;
    private Date dataAtualizacao;

    private ListaTarefa listaTarefa;

    private StatusTarefa status;

    public Tarefa() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "tarefa", nullable = false)
    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String name_task) {
        this.tarefa = name_task;
    }

    @Column(name = "data_criacao", nullable = false)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date date_create) {
        this.dataCriacao = date_create;
    }

    @Column(name = "data_atualizacao")
    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date date_updated) {
        this.dataAtualizacao = date_updated;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public ListaTarefa getListaTarefa() {
        return listaTarefa;
    }

    public void setListaTarefa(ListaTarefa task_list) {
        this.listaTarefa = task_list;
    }

    @Column(name = "status", nullable = false)
    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }


    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        Date dataAtual = new Date();
        dataCriacao = dataAtual;
        dataAtualizacao = dataAtual;
    }


    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", tarefa=" + tarefa + ", dataCriacao=" + dataCriacao
                + ", dataAtualizacao=" + dataAtualizacao + ", status" + status + "]";
    }

}
