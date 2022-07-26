package com.ngtechnology.estacionamento.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvaga;
    private Boolean disponivel;

    public Vagas(){

    }

    public Vagas(Long idvaga, Boolean disponivel){
        this.idvaga = idvaga;
        this.disponivel = disponivel;
    }

    public Long getIdvaga() {
        return idvaga;
    }

    public void setIdvaga(Long idvaga) {
        this.idvaga = idvaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
