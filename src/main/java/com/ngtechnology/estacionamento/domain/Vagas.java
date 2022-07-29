package com.ngtechnology.estacionamento.domain;

import javax.persistence.*;

@Entity
@Table (name = "vagas")
public class  Vagas {

    /**@Column o que faz essa anotação? **/
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
