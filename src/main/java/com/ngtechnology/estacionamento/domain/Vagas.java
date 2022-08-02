package com.ngtechnology.estacionamento.domain;

import javax.persistence.*;

@Entity
@Table (name = "vagas")
public class Vagas {

    /**@Column o que faz essa anotação? **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvaga;
    private Boolean disponivel;

    public Vagas(){

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

    public Vagas withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public Vagas withBuilderVagasId(Long id){
        setIdvaga(id);
        return this;
    }

}
