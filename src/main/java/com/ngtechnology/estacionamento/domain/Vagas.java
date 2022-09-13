package com.ngtechnology.estacionamento.domain;

import javax.persistence.*;

@Entity
@Table (name = "tb_vagas")
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")

    private Long idVaga;
    @Column(name = "disponivel")
    private Boolean disponivel;

    public Vagas(){

    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdvaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Vagas withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public Vagas withBuilderVagasId(Long id){
        setIdvaga(id);
        return this;
    }

}
